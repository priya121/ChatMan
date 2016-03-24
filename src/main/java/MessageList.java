import java.io.Serializable;

public class MessageList implements Serializable {
    int counter = 0;
    String message = "";
    String batchMessage = "";

    public String set(IOConsole io) {
        while (counter < 3) {
            message = io.getInput();
            batchMessage += message + "\n";
            counter ++;
        }
        return batchMessage;
    }
}
