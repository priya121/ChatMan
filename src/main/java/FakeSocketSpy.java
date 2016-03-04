import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FakeSocketSpy implements ConnectionSocket {
    boolean called = false;
    boolean closed = false;

    @Override
    public OutputStream getOutputStream() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(10);
        try {
            byteArrayOutputStream.write("Hi".getBytes());
            called = true;
            return byteArrayOutputStream;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public void close() {
        closed = true;
    }

    public boolean hasGotOutputStream() {
        return called;
    }

    public boolean hasClosed() {
        return closed;
    }
}
