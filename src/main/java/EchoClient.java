import java.io.IOException;

public interface EchoClient {
    void writeDataToClient(ConnectionSocket socket) throws IOException;
}
