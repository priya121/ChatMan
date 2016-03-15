import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements EchoServer {
    private final IOConsole io;

    public Server(IOConsole io) {
        this.io = io;
    }

    @Override
    public void readDataFromClient(ServerSocket serverSocket) throws IOException {
        while (true) {
            Socket socket = serverSocket.accept();
            ClientSocket clientSocket = new ClientSocket(socket);
            echo(clientSocket);
        }
    }

    @Override
    public String echo(ConnectionSocket socket) throws IOException {
        BufferedReader inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String clientMessage = inFromClient.readLine();
        while (clientMessage != null) {
            clientMessage = echoBackMessage(socket, inFromClient, clientMessage);
        }
        return clientMessage;
    }

    private String echoBackMessage(ConnectionSocket socket, BufferedReader buffer, String clientMessage) throws IOException {
        io.showOutput(clientMessage + "\n");
        writeBackToClient(writeToBuffer(clientMessage), socket);
        clientMessage = buffer.readLine();
        return clientMessage;
    }

    private BufferedReader writeToBuffer(String echoedWord) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(echoedWord.getBytes());
        return new BufferedReader(new InputStreamReader(inputStream));
    }

    public String writeBackToClient(BufferedReader userInput, ConnectionSocket socket) throws IOException {
        String word = userInput.readLine();
        StreamWriter outToServer = socket.createOutputStream();
        outToServer.writeBytes(word + '\n');
        return word;
    }

}
