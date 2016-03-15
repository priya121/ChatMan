import java.io.InputStream;
import java.io.OutputStream;

public interface ConnectionSocket extends AutoCloseable {
    StreamWriter createOutputStream();
    OutputStream getOutputStream();
    InputStream getInputStream();
}
