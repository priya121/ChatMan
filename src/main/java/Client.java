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
    public void writeDataToClient(ConnectionSocket socket) throws IOException {
        String userInput = getUserInput();
        while (!userInput.equals("quit")) {
            BufferedReader bufferedReader = writeInputToBufferedReader(userInput);
            writeToServer(bufferedReader, socket);
            readInFromServer(socket);
            userInput = io.getInput();
        }
    }

    private String getUserInput() {
        io.showOutput("Enter a word to echo back (type quit to exit): ");
        return io.getInput();
    }

    private void readInFromServer(ConnectionSocket socket) throws IOException {
        BufferedReader inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        io.showOutput(inFromClient.readLine());
    }

    private void writeToServer(BufferedReader userInput, ConnectionSocket connectionSocket) throws IOException {
        String word = userInput.readLine();
        StreamWriter outToServer = connectionSocket.createOutputStream();
        outToServer.writeBytes(word + '\n');
    }

    private BufferedReader writeInputToBufferedReader(String userInput) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        return new BufferedReader(new InputStreamReader(inputStream));
    }
}



