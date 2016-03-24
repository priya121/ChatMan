import org.junit.Test;

import java.io.*;
import java.net.ServerSocket;

import static org.junit.Assert.assertEquals;

public class ServerClientTest {
    ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(recordedOutput);

    @Test
    public void testsAllMessagesSentToClient() throws IOException {
        UserIO console = convertUserInput(new ByteArrayInputStream("localhost\n4444\n".getBytes()));
        UserIO programConsole = convertUserInput(new ByteArrayInputStream("how are you?\nI'm great thanks\nThat's good\nok\ngood\nquit\nbye\nquit\n".getBytes()));
        ChatClient client = new ChatClient(programConsole);
        ConnectionSocket clientServerConnection = connectClientToServer(4444, console);
        client.startChat(clientServerConnection);
        assertEquals("Please type in localhost make a connection\n" +
                     "Please enter 4444 to make a connection\n" +
                     "Enter text to send: \n" +
                     "how are you?\n" +
                     "I'm great thanks\n" +
                     "That's good\n\n" +
                     "how are you?\n" +
                     "I'm great thanks\n" +
                     "That's good\n\n" +
                     "ok\n" +
                     "good\n" +
                     "quit\n\n" +
                     "ok\n" +
                     "good\n" +
                     "quit\n\n", recordedOutput.toString());
    }

    private ConnectionSocket connectClientToServer(int port, IOConsole console) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        Server server = new Server(console, serverSocket);
        ClientSocket clientSocket = new SocketConnector(console).connect();
        server.read();
        return clientSocket;
    }

    public UserIO convertUserInput(InputStream userInput) {
        return new UserIO(userInput, output);
    }
}
