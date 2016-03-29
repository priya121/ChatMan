import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ChatClient implements Client {

    private final IOConsole io;

    public ChatClient(IOConsole io) {
        this.io = io;
    }

    @Override
    public void startChat(ConnectionSocket socket) throws IOException {
        io.showOutput("Enter text to send: ");
        List<String> userMessage = new ArrayList<>();
        while (!userMessage.contains("quit")) {
            writeToServer(socket);
            userMessage = readInFromServer(socket);
        }
        try {
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeToServer(ConnectionSocket connectionSocket) throws IOException {
        MessageList batch = createMessageList();
        if (batch.isReadyForSend()) {
            StreamWriter outToServer = connectionSocket.getOutputStream();
            batch.writeToStream(outToServer);
        }
    }

    private MessageList createMessageList() {
        MessageListFactory messageListFactory = new MessageListFactory(io);
        return messageListFactory.create();
    }

    public List<String> readInFromServer(ConnectionSocket socket) throws IOException {
        InputStream inputFromClient = socket.getInputStream();
        ObjectInputStream batch = new ObjectInputStream(inputFromClient);
        MessageList messageFromClient = null;
        try {
            messageFromClient = (MessageList) batch.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        messageFromClient.getMessages().forEach(io::showOutput);
        return messageFromClient.getMessages();
    }
}
