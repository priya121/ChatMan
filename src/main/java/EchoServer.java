import java.io.IOException;

public interface EchoServer extends Runnable {
    void readDataFromClient() throws IOException;
    String echoLoop() throws IOException;
}
