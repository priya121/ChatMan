import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ThreadServer implements EchoServer {

    private final IOConsole io;
    private final ConnectionSocket socket;

    public ThreadServer(ConnectionSocket socket, IOConsole io) {
        this.socket = socket;
        this.io = io;
    }

    @Override
    public void run() {
        try {
            readDataFromClient();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readDataFromClient() throws IOException {
        echoLoop();
    }

    public String echoLoop() throws IOException {
        BufferedReader inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String clientMessage = inFromClient.readLine();
        while (clientMessage != null) {
            clientMessage = echoBackMessage(socket, inFromClient, clientMessage);
        }
        return clientMessage;
    }

    private String echoBackMessage(ConnectionSocket socket, BufferedReader buffer, String clientMessage) throws IOException {
        io.showOutput(clientMessage + "\n");
        String response = io.getInput();
        writeBackToClient(writeToBuffer(response), socket);
        clientMessage = buffer.readLine();
        return clientMessage;
    }

    private BufferedReader writeToBuffer(String echoedWord) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(echoedWord.getBytes());
        return new BufferedReader(new InputStreamReader(inputStream));
    }

    public String writeBackToClient(BufferedReader userInput, ConnectionSocket socket) throws IOException {
        String word = userInput.readLine();
        StreamWriter out = socket.createOutputStream();
        out.writeBytes(word + '\n');
        return word;
    }
}
