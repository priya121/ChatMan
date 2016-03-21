import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
        IOConsole fakeInput = new FakeIO(Arrays.asList("localhost", "4444", "4444"));
        ConnectionSocket connector = new SocketConnector(fakeInput).connect();
        ServerSocket serverSocket = new ServerSocket(4444);
        Server server = new Server(fakeInput, serverSocket);
        server.read();
        assertTrue(connector instanceof ConnectionSocket);
    }
}
