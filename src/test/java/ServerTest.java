import org.junit.Test;

import java.io.*;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class ServerTest {
    IOConsole fakeInput = new FakeIO(Arrays.asList("Hi", "How", "Are", "you"));
    ConnectionSocket fakeSocket = new FakeSocketSpy();
    ThreadServer server = new ThreadServer(fakeSocket, fakeInput);
    ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(recordedOutput);

    @Test
    public void displaysOutWordsSentFromClientSocket() throws IOException {
        EchoConsole console = convertUserInput(new ByteArrayInputStream("Hi\nHi how are you?".getBytes()));
        ThreadServer server = new ThreadServer(fakeSocket, console);
        server.echoLoop();
        assertEquals("Hi how are you?\n\n" +
                "quit\n\n", recordedOutput.toString());
    }

    @Test
    public void writesBackWordToClient() throws IOException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("Hi".getBytes());
        BufferedReader holder = new BufferedReader(new InputStreamReader(inputStream));
        assertEquals("Hi", server.writeBackToClient(holder, fakeSocket));
    }

    public EchoConsole convertUserInput(InputStream userInput) {
        return new EchoConsole(userInput, output);
    }
}