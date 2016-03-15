import java.io.*;

public class FakeSocketSpy implements ConnectionSocket {
    public boolean called = false;
    public boolean closed = false;

    @Override
    public OutputStream getOutputStream() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(10);
        try {
            byteArrayOutputStream.write("Hi how are you?\nquit".getBytes());
            return byteArrayOutputStream;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public InputStream getInputStream() {
        InputStream inputStream = new ByteArrayInputStream("Hi how are you?\nquit".getBytes());
        return inputStream;
    }

    @Override
    public FakeStreamWriter createOutputStream() {
        called = true;
        return new FakeStreamWriter();
    }

    public boolean hasGotOutputStream() {
        return called;
    }

    @Override
    public void close() throws Exception {
       closed = true;
    }
}
