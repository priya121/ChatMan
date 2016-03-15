import java.io.IOException;
import java.net.Socket;

public class SocketConnector {
    private final IOConsole io;
    String IPAddress;
    int port;

    public SocketConnector(IOConsole io) {
        this.io = io;
    }

    public void userInput() {
        io.showOutput("Please type in localhost make a connection");
        IPAddress = io.getInput();
        io.showOutput("Please enter 4444 to make a connection");
        port = Integer.parseInt(io.getInput());
    }

    public ConnectionSocket connect() {
        userInput();
        try {
            io.showOutput("Connection made");
            return new ClientSocket(new Socket(IPAddress, port));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
