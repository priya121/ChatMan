import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class App {

    public static void main(String[] args) throws IOException {
        String IPAddress = "localhost";
        int port = 4444;
        IOConsole console = new EchoConsole(System.in, System.out);
        Server server = new Server(console);
        Client client = new Client(console);

        if (args[0].equals("in"))
            try (ConnectionSocket realSocket = new ClientSocket(new Socket(IPAddress, port))) {
                client.writeDataToClient(realSocket);
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

