import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Client implements EchoClient {

    private final IOConsole io;

    public Client(IOConsole io) {
        this.io = io;
    }

    @Override
    public void writeDataToServer(ConnectionSocket socket) throws IOException {
        String userInput = getUserInput();
        while (!userInput.contains("quit")) {
            writeToServer(userInput, socket);
            readInFromServer(socket);
            userInput = io.getInput();
        }
    }

    private String getUserInput() {
        io.showOutput("Enter text to send: ");
        return io.getInput();
    }

    public void readInFromServer(ConnectionSocket socket) throws IOException {
        BufferedReader inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        io.showOutput(inFromClient.readLine());
    }

    private void writeToServer(String userInput, ConnectionSocket connectionSocket) throws IOException {
        BufferedReader bufferedReader = writeToBuffer(userInput);
        String word = bufferedReader.readLine();
        StreamWriter outToServer = connectionSocket.getOutputStream();
        outToServer.writeBytes(word + " " + "\n");
    }

    private BufferedReader writeToBuffer(String userInput) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        return new BufferedReader(new InputStreamReader(inputStream));
    }
}
