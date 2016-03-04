import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class RealSocket implements ConnectionSocket {
    private final Socket socket;

    public RealSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public OutputStream getOutputStream() {
        try {
            return socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
            throw (new RuntimeException());
        }
    }

    @Override
    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw (new RuntimeException());
        }
    }
}
