import org.junit.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class ServerTest {
    IOConsole fakeInput = new FakeIO(Arrays.asList("Hi"));
    ConnectionSocket fakeSocket = new FakeSocketSpy();
    Server server = new Server(fakeInput);

    @Test
    public void displaysOutWordsSentFromClientSocket() throws IOException {
        assertEquals(server.echo(fakeSocket), null);
    }

    @Test
    public void writesBackWordToClient() throws IOException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("Hi".getBytes());
        BufferedReader holder =  new BufferedReader(new InputStreamReader(inputStream));
        assertEquals("Hi", server.writeBackToClient(holder, fakeSocket));
    }
}