import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class ServerTest {
    IOConsole fakeInput = new FakeIO(Arrays.asList("Hi"));
    FakeSocketSpy fakeSocket = new FakeSocketSpy();

    @Test
    public void testsOutputProduced() throws IOException {
        Server server = new Server(fakeInput);
        assertEquals(server.echo(fakeSocket), "Hi how are you?");
    }
}