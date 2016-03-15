import java.io.IOException;
import java.net.ServerSocket;

public interface EchoServer {
    void readDataFromClient(ServerSocket serverSocket) throws IOException;
    String echo(ConnectionSocket connectionSocket) throws IOException;
}
