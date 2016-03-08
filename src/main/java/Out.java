import java.io.IOException;

public interface Out extends AutoCloseable {
    void writeDataOut(ConnectionSocket socket) throws IOException;
}
