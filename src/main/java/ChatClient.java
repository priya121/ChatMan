import java.io.*;

public class ChatClient implements Client {

    private final IOConsole io;

    public ChatClient(IOConsole io) {
        this.io = io;
    }

    @Override
    public void startChat(ConnectionSocket socket) throws IOException {
        io.showOutput("Enter text to send: ");
        String userMessage = "";
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
        String batch = new MessageList().set(io);
        StreamWriter outToServer = connectionSocket.getOutputStream();
        outToServer.writeObject(batch);
    }

    public String readInFromServer(ConnectionSocket socket) throws IOException {
        InputStream inputFromClient = socket.getInputStream();
        try {
            ObjectInputStream batch = new ObjectInputStream(inputFromClient);
            String messageFromClient = (String) batch.readObject();
            io.showOutput(messageFromClient);
            return messageFromClient;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
