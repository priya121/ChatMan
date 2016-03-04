import java.io.IOException;
import java.net.Socket;

public class App {

    public static void main(String[] args) throws IOException {
        String IPAddress = "localhost";
        int port = 44444;
        IOConsole console = new EchoConsole(System.in, System.out);
        Server server = new Server(port, console);
        Client client = new Client(console);


        if (args[0].equals("in")) try {
            ConnectionSocket realSocket = new RealSocket(new Socket(IPAddress, port));
            OutputStreamCreator outputStreamCreator = new RealOutputStreamCreator();
            client.writeDataOut(realSocket, outputStreamCreator);
        } catch (IOException e) {
            console.showOutput("Invalid input");
            e.printStackTrace();
        }
        else if (args[0].equals("out"))
            server.readDataIn();
    }
}

