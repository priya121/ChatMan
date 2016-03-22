import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ServerReadInThread implements EchoServer {

    private final IOConsole io;
    private final ConnectionSocket socket;
    public static List<String> messageHistory;

    public ServerReadInThread(ConnectionSocket socket, IOConsole io) {
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
        BufferedReader buffer = readInputStreamToBuffer();
        String clientMessage = buffer.readLine();
        while (clientMessage != null) {
            messageHistory.add(clientMessage);
            showAllMessages(messageHistory);
            echoBackMessage(socket, buffer);
            clientMessage = buffer.readLine();
        }
    }

    private BufferedReader readInputStreamToBuffer() {
        InputStream inputFromClient = socket.getInputStream();
        InputStreamReader inputReader = new InputStreamReader(inputFromClient);
        return new BufferedReader(inputReader);
    }

    private void echoBackMessage(ConnectionSocket socket, BufferedReader buffer) throws IOException {
        writeBackToClient(writeMessagesToBuffer(), socket);
    }

    public void writeBackToClient(BufferedReader bufferedReader, ConnectionSocket socket) throws IOException {
        String word;
        StreamWriter out = socket.getOutputStream();
        while ((word = bufferedReader.readLine()) != null) {
            out.writeBytes(word + '\n');
        }
    }

    private BufferedReader writeMessagesToBuffer() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        writeMessagesToStream(outputStream);
        byte[] bytes = outputStream.toByteArray();
        return createBufferedReader(bytes);
    }

    private BufferedReader createBufferedReader(byte[] bytes) {
        InputStream inputStream = new ByteArrayInputStream(bytes);
        return new BufferedReader(new InputStreamReader(inputStream));
    }

    private void writeMessagesToStream(ByteArrayOutputStream outputStream) {
        for (String word : messageHistory) {
            try {
                outputStream.write(word.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void showAllMessages(List<String> messageHistory) {
        messageHistory.forEach(io::showOutput);
    }

}
