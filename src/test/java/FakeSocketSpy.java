import java.io.*;

public class FakeSocketSpy implements ConnectionSocket {
    private final IOConsole fakeIOStream;
    public boolean called = false;
    public boolean closed = false;

    public FakeSocketSpy(IOConsole fakeIOStream) {
        this.fakeIOStream = fakeIOStream;
    }

    @Override
    public FakeStreamWriter getOutputStream() {
        called = true;
        FakeStreamWriter fakeWriter = new FakeStreamWriter();
        fakeWriter.writeBytes(fakeIOStream.getInput());
        return fakeWriter;
    }

    @Override
    public InputStream getInputStream() {
        InputStream inputStream = new ByteArrayInputStream(fakeIOStream.getInput().getBytes());
        return inputStream;
    }

    @Override
    public void close() throws Exception {
        closed = true;
    }
}
