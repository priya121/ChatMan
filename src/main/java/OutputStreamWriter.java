import java.io.DataOutputStream;
import java.io.IOException;

public class OutputStreamWriter implements StreamWriter {
    public DataOutputStream dataOutputStream;

    public OutputStreamWriter(DataOutputStream dataOutputStream) {
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
