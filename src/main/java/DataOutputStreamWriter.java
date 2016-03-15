import java.io.DataOutputStream;
import java.io.IOException;

public class DataOutputStreamWriter implements StreamWriter {
    private DataOutputStream dataOutputStream;

    public DataOutputStreamWriter(DataOutputStream dataOutputStream) {
        this.dataOutputStream = dataOutputStream;
    }

    @Override
    public void writeBytes(String word) {
        try {
            dataOutputStream.writeBytes(word);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
