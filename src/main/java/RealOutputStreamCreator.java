import java.io.DataOutputStream;
import java.io.OutputStream;

public class RealOutputStreamCreator implements OutputStreamCreator {

    @Override
    public DataOutput createOutput(OutputStream outputStream) {
        return new RealOutputStream(new DataOutputStream(outputStream));
    }
}
