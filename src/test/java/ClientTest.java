import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ClientTest {
    IOConsole fakeInput = new FakeIO(Arrays.asList(""));
    Client client = new Client(fakeInput);
    FakeSocketSpy fakeSocket = new FakeSocketSpy();

    @Test
    public void socketProducesOutputStream() throws IOException {
        FakeSocketSpy fakeSocket = new FakeSocketSpy();
        client.writeDataOut(fakeSocket);
        assertTrue(fakeSocket.hasGotOutputStream());
    }

    @Test
    public void closesOutputStream() throws IOException {
        client.writeDataOut(fakeSocket);
        assertTrue(fakeSocket.hasClosed());
    }

    @Test
    public void createsAnOutputStream() {
        assertEquals(fakeSocket.getOutputStream().toString(), "Hi how are you?");
    }

    @Test
    public void writesDataOut() throws IOException {
        FakeByteStreamWriter fakeDataOutput = fakeSocket.createOutputStream(fakeSocket.getOutputStream());
        fakeDataOutput.writeBytes("Hi how");
        assertEquals(fakeDataOutput.getWrittenBytes(), "Hi how");
    }

}

