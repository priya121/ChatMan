import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

public class ChatServer implements EchoServer {

    private final IOConsole io;
    private final ConnectionSocket socket;

    public ChatServer(ConnectionSocket socket, IOConsole io) {
        this.socket = socket;
        this.io = io;
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
        String clientMessage = readClientMessage();
        while (clientMessage != null) {
            showAllMessages(clientMessage);
            echoBackMessages(socket, clientMessage);
            clientMessage = readClientMessage();
        }
    }

    private String readClientMessage() {
        InputStream inputFromClient = socket.getInputStream();
        try {
            ObjectInputStream batch = new ObjectInputStream(inputFromClient);
            String messageFromClient = (String) batch.readObject();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            writeMessagesToStream(outputStream, messageFromClient);
            return messageFromClient;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void echoBackMessages(ConnectionSocket socket, String words) throws IOException {
        StreamWriter outToServer = socket.getOutputStream();
        outToServer.writeObject(words);
    }

    private void writeMessagesToStream(ByteArrayOutputStream outputStream, String words) {
        try {
            outputStream.write(words.getBytes());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void showAllMessages(String batchMessage) {
        io.showOutput(batchMessage);
    }

}
