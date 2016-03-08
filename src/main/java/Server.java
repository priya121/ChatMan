import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements In {
    private final IOConsole io;

    public Server(IOConsole io) {
        this.io = io;
    }

    public void readDataIn(ServerSocket serverSocket) throws IOException {
        while (true) {
            Socket socket = serverSocket.accept();
            ClientSocket clientSocket = new ClientSocket(socket);
            echo(clientSocket);
        }
    }

    public String echo(ConnectionSocket clientSocket) throws IOException {
        BufferedReader inFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String clientMessage = inFromClient.readLine();
        io.showOutput(clientMessage + "\n");
        return clientMessage;
    }

    @Override
    public void close() throws Exception {
    }
}
