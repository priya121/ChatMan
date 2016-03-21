import java.io.InputStream;

public interface ConnectionSocket extends AutoCloseable {
    StreamWriter getOutputStream();
    InputStream getInputStream();
}
