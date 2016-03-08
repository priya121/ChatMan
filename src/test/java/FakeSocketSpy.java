import java.io.*;

public class FakeSocketSpy implements ConnectionSocket {
    boolean called = false;
    boolean closed = false;

    @Override
    public OutputStream getOutputStream() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(10);
        try {
            byteArrayOutputStream.write("Hi how are you?".getBytes());
            called = true;
            close();
            return byteArrayOutputStream;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public InputStream getInputStream() {
        InputStream inputStream = new ByteArrayInputStream("Hi how are you?".getBytes());
        return inputStream;
    }

    @Override
    public FakeByteStreamWriter createOutputStream(OutputStream outputStream) {
        return new FakeByteStreamWriter();
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
