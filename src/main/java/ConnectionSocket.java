import java.io.InputStream;
import java.io.OutputStream;

public interface ConnectionSocket extends AutoCloseable {
    BytesToStreamWriter createOutputStream();
    OutputStream getOutputStream();
    InputStream getInputStream();
}
