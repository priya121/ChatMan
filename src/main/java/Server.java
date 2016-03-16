import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final IOConsole io;
    private final ServerSocket serverSocket;

    public Server(IOConsole io, ServerSocket serverSocket) {
        this.io = io;
        this.serverSocket = serverSocket;
    }

    public void read() throws IOException {
        while (true) {
            Socket socket = serverSocket.accept();
            new Thread(new ThreadServer(new ClientSocket(socket), io), "newThread").start();
        }
    }
}


