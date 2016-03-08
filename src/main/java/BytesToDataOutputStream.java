import java.io.DataOutputStream;
import java.io.IOException;

public class BytesToDataOutputStream implements BytesToStreamWriter {
    private DataOutputStream dataOutputStream;

    public BytesToDataOutputStream(DataOutputStream dataOutputStream) {
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
