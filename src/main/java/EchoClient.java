import java.io.IOException;

public interface EchoClient extends AutoCloseable {
    void writeDataToClient(ConnectionSocket socket) throws IOException;
}
