import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketAcceptor {

    public Socket acceptClient(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            Socket clientAcceptedSocket = serverSocket.accept();
            return clientAcceptedSocket;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
