import java.io.IOException;

public interface EchoClient {
    void writeDataToServer(ConnectionSocket socket) throws IOException;
}
