import org.junit.Test;

import java.util.Arrays;

import static junit.framework.TestCase.assertTrue;
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

    @Test
    public void makesAConnectionToASocketWithUserInput() {
        IOConsole fakeInput = new FakeIO(Arrays.asList("localhost", "4444"));
        SocketConnector connector = new SocketConnector(fakeInput);
        assertTrue(connector.connect() instanceof ClientSocket);
    }
}
