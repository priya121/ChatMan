import java.io.IOException;

public interface Out {
    void writeDataOut(ConnectionSocket socket, OutputStreamCreator outputStreamCreator) throws IOException;
}
