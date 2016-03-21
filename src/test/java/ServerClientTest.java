import org.junit.Test;

import java.io.*;
import java.net.ServerSocket;

import static org.junit.Assert.assertTrue;

public class ServerClientTest {
    ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(recordedOutput);
    EchoConsole console = convertUserInput(new ByteArrayInputStream("localhost\n4444\nhow are you?\nI'm great thanks\nThat's good\nquit\n".getBytes()));
    Client client = new Client(console);

    @Test
    public void testsAllMessagesSentToClient() throws IOException {
        ConnectionSocket clientSocket = connectClientToServer(4444);
        client.writeDataToServer(clientSocket);
        assertTrue(recordedOutput.toString().contains("how are you? I'm great thanks That's good"));
    }

    private ConnectionSocket connectClientToServer(int port) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        Server server = new Server(console, serverSocket);
        ClientSocket clientSocket = new SocketConnector(console).connect();
        server.read();
        return clientSocket;
    }


    public EchoConsole convertUserInput(InputStream userInput) {
        return new EchoConsole(userInput, output);
    }
}
