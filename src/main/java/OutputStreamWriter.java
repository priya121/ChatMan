import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class OutputStreamWriter implements StreamWriter {
    public DataOutputStream dataOutputStream;
    public ObjectOutputStream objectOutputStream;

    public OutputStreamWriter(DataOutputStream dataOutputStream, ObjectOutputStream objectOutputStream) {
        this.dataOutputStream = dataOutputStream;
        this.objectOutputStream = objectOutputStream;
    }

    @Override
    public void writeBytes(String word) {
        try {
            dataOutputStream.writeBytes(word);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeObject(String batch) {
        try {
            objectOutputStream.writeObject(batch);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
