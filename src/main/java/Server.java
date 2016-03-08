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
    public String echo(ConnectionSocket clientSocket) throws IOException {
        BufferedReader inFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String clientMessage = inFromClient.readLine();
        while (clientMessage != null) {
            io.showOutput(clientMessage + "\n");
            writeBackToClient(writeToBufferedReader(clientMessage), clientSocket);
            clientMessage = inFromClient.readLine();
        }
        return clientMessage;
    }

    private BufferedReader writeToBufferedReader(String echoedWord) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(echoedWord.getBytes());
        return new BufferedReader(new InputStreamReader(inputStream));
    }

    public String writeBackToClient(BufferedReader userInput, ConnectionSocket connectionSocket) throws IOException {
        String word = userInput.readLine();
        BytesToStreamWriter outToServer = connectionSocket.createOutputStream();
        outToServer.writeBytes(word + '\n');
        return word;
    }

    @Override
    public void close() throws Exception {
    }
}
