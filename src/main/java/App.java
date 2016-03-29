import java.io.IOException;
import java.net.ServerSocket;

public class App {

    public static void main(String[] args) throws IOException {
        IOConsole console = new UserIO(System.in, System.out);

        if (args[0].equals("in")) {
            SocketConnector connect = new SocketConnector(console);
            ConnectionSocket socket = connect.connect();
            ChatClient client = new ChatClient(console);
            client.startChat(socket);
        } else if (args[0].equals("out")) {
            ServerSocket serverSocket = new ServerSocket(4444);
            Server server = new Server(console, serverSocket);
            server.read();
        }
    }
}

