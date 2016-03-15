import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class SocketConnectorTest {

    @Test
    public void asksUserToTypeInIPAddressAndPortNumber() {
        IOConsole fakeInput = new FakeIO(Arrays.asList("localhost", "4444"));
        SocketConnector connector = new SocketConnector(fakeInput);
        connector.userInput();
        assertEquals("localhost", connector.IPAddress);
        assertEquals(4444, connector.port);
    }

    @Ignore
    @Test
    public void makesAConnectionToASocketWithUserInput() throws IOException {
        IOConsole fakeInput = new FakeIO(Arrays.asList("localhost", "5454"));
        SocketConnector connector = new SocketConnector(fakeInput);
        Client client = new Client(fakeInput);
        client.writeDataToClient(connector.connect());
    }
}
