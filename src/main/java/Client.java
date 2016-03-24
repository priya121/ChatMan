import java.io.IOException;

public interface Client {
    void startChat(ConnectionSocket socket) throws IOException;
}
