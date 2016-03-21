import org.junit.Test;

import java.io.*;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ClientTest {
    IOConsole fakeInput = new FakeIO(Arrays.asList("hi", "how are you?", "I'm good", "quit"));
    Client client = new Client(fakeInput);
    FakeSocketSpy fakeSocket = new FakeSocketSpy(fakeInput);
    ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(recordedOutput);

    @Test
    public void socketProducesOutputStream() throws IOException {
        client.writeDataToServer(fakeSocket);
        assertTrue(fakeSocket.hasOutputStream());
    }

    @Test
    public void connectionSocketIsClosedAfterUsed() {
        boolean closed = false;
        try (ConnectionSocket fake = new FakeSocketSpy(fakeInput)) {
            fake.getOutputStream();
            closed = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(closed, true);
    }

    @Test
    public void canGetOutputStreamFromSocket() throws IOException {
        FakeStreamWriter fakeDataOutput = fakeSocket.getOutputStream();
        assertEquals("hi", fakeDataOutput.getWrittenBytes());
    }

    @Test
    public void canReadInContentFromServer() throws IOException {
        EchoConsole console = convertUserInput(new ByteArrayInputStream("how are you?\n".getBytes()));
        Client client = new Client(console);
        ConnectionSocket fakeConnectionSocket = new FakeSocketSpy(console);
        client.readInFromServer(fakeConnectionSocket);
        assertEquals("how are you?\n", recordedOutput.toString());
    }

    public EchoConsole convertUserInput(InputStream userInput) {
        return new EchoConsole(userInput, output);
    }
}

