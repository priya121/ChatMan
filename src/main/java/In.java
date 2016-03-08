import java.io.IOException;
import java.net.ServerSocket;

public interface In extends AutoCloseable{
    void readDataIn(ServerSocket serverSocket) throws IOException;
}
