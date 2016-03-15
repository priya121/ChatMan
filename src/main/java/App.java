import java.io.IOException;
import java.net.ServerSocket;

public class App {

    public static void main(String[] args) throws IOException {
        int port = 4444;
        IOConsole console = new EchoConsole(System.in, System.out);
        Server server = new Server(console);
        Client client = new Client(console);
        SocketConnector connect = new SocketConnector(console);

        if (args[0].equals("in"))
            try (ConnectionSocket socket = connect.connect()) {
                client.writeDataToClient(socket);
            } catch (Exception e) {
                console.showOutput("Invalid client socket");
                e.printStackTrace();
            }
        else if (args[0].equals("out")) {
            try (ServerSocket serverSocket = new ServerSocket(port)) {
                server.readDataFromClient(serverSocket);
            } catch (Exception e) {
                console.showOutput("Invalid server socket");
                e.printStackTrace();
            }
        }
    }
}

