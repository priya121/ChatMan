import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ClientTest {
    IOConsole fakeInput = new FakeIO(Arrays.asList("hi","quit"));
    Client client = new Client(fakeInput);
    FakeSocketSpy fakeSocket = new FakeSocketSpy();

    @Test
    public void socketProducesOutputStream() throws IOException {
        client.writeDataToClient(fakeSocket);
        fakeSocket.createOutputStream();
        assertTrue(fakeSocket.hasGotOutputStream());
    }

    @Test
    public void closesOutputStreamByImplementingAutocloseable() {
        boolean closed = false;
        try (ConnectionSocket fake = new FakeSocketSpy()) {
            fake.createOutputStream();
            closed = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(closed, true);
    }

    @Test
    public void createsAnOutputStream() {
        assertEquals(fakeSocket.getOutputStream().toString(), "Hi how are you?\nquit");
    }

    @Test
    public void writesDataOut() throws IOException {
        FakeByteStreamWriter fakeDataOutput = fakeSocket.createOutputStream();
        fakeDataOutput.writeBytes("Hi how");
        assertEquals(fakeDataOutput.getWrittenBytes(), "Hi how");
    }
}

