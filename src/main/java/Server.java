import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements In {
    private final IOConsole io;
    private int port;

    public Server(int port, IOConsole io) {
        this.port = port;
        this.io = io;
    }

    public void readDataIn() throws IOException {
        String clientMessage;
        ServerSocket serverSocket = new ServerSocket(port);

        while(true) {
            Socket clientSocket = serverSocket.accept();
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(clientSocket.getOutputStream());
            while (!inFromClient.readLine().contains("quit")) {
                clientMessage = inFromClient.readLine();
                io.showOutput(clientMessage + "\n");
                clientMessage = inFromClient.readLine();
                outToClient.writeBytes(clientMessage);
            }
        }
    }
}
