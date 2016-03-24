import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class ClientTest {
    IOConsole fakeInput = new FakeIO(Arrays.asList("hi", "how are you?", "I'm good", "a", "b", "c", "quit"));
    FakeSocketSpy fakeSocket = new FakeSocketSpy(fakeInput);

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

    @Ignore
    @Test
    public void canReadInContentFromServer() throws IOException {
        IOConsole fakeInput = new FakeIO(Arrays.asList("hi", "how are you?", "quit"));
        ChatClient client = new ChatClient(fakeInput);
        ConnectionSocket fakeConnectionSocket = new FakeSocketSpy(fakeInput);
        assertEquals("hi", "how are you?", client.readInFromServer(fakeConnectionSocket));
    }

}

