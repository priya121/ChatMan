import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class ChatServer implements EchoServer {

    private final IOConsole io;
    private final ConnectionSocket socket;
    private final List<String> messageHistory;

    public ChatServer(ConnectionSocket socket, IOConsole io) {
        this.socket = socket;
        this.io = io;
        messageHistory = new ArrayList<>();
    }

    @Override
    public void run() {
        try {
            sendMessagesToClient();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessagesToClient() throws IOException {
        MessageList clientMessage = readClientMessage();
        while (clientMessage.getMessages() != null) {
            showAllMessages(clientMessage);
            storeAllMessages(clientMessage);
            clientMessage = readClientMessage();
        }
    }

    private MessageList readClientMessage() {
        InputStream inputFromClient = socket.getInputStream();
        try {
            ObjectInputStream batch = new ObjectInputStream(inputFromClient);
            MessageList messageFromClient = (MessageList) batch.readObject();
            StreamWriter outToServer = socket.getOutputStream();
            writeMessagesToStream(outToServer, messageFromClient);
            return messageFromClient;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void writeMessagesToStream(StreamWriter outputStream, MessageList words) {
        words.writeToStream(outputStream);
    }

    public void showAllMessages(MessageList batchMessage) {
        batchMessage.getMessages().forEach(io::showOutput);
    }

    public void storeAllMessages(MessageList batchMessage) {
        for (String message : batchMessage.getMessages()) {
            messageHistory.add(message);
        }
    }
}
