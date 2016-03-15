import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientSocket implements ConnectionSocket {
    private final Socket socket;

    public ClientSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public OutputStream getOutputStream() {
        try {
            return socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public InputStream getInputStream() {
        try {
            return socket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public StreamWriter createOutputStream() {
        return new DataOutputStreamWriter(new DataOutputStream(getOutputStream()));
    }

    @Override
    public void close() throws Exception {
    }

}
