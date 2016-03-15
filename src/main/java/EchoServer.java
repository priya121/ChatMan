import java.io.IOException;
import java.net.ServerSocket;

public interface EchoServer {
    void readDataFromClient(ServerSocket serverSocket) throws IOException;
    String echoLoop(ConnectionSocket connectionSocket) throws IOException;
}
