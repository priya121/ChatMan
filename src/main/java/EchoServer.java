import java.io.IOException;

public interface EchoServer extends Runnable {
    void sendMessagesToClient() throws IOException;
}
