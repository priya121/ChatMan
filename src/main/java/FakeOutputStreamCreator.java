import java.io.OutputStream;

public class FakeOutputStreamCreator implements OutputStreamCreator {

    @Override
    public FakeOutputStream createOutput(OutputStream outputStream) {
        return new FakeOutputStream();
    }
}
