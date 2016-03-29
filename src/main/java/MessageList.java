import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MessageList implements Serializable {
    List<String> messages = new ArrayList<>();

    public void addMessage(String message) {
        messages.add(message);
    }

    public List<String> getMessages() {
        return messages;
    }

    public boolean isReadyForSend() {
        return messages.size() == 3;
    }

    public void writeToStream(StreamWriter outputStream) {
        outputStream.writeObject(this);
    }
}
