import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Client implements Out {

    private final IOConsole io;

    public Client(IOConsole io) {
        this.io = io;
    }

    @Override
    public void writeDataOut(ConnectionSocket socket, OutputStreamCreator outputStreamCreator) throws IOException {
        BufferedReader userInput = writeInputToBufferedReader();
        writeToServer(userInput, socket, outputStreamCreator);
        socket.close();
    }

    private void writeToServer(BufferedReader userInput, ConnectionSocket connectionSocket, OutputStreamCreator outputStreamCreator) throws IOException {
        String word = userInput.readLine();
        DataOutput outToServer = outputStreamCreator.createOutput(connectionSocket.getOutputStream());
        outToServer.writeBytes(word + '\n');
    }

    private BufferedReader writeInputToBufferedReader() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(io.getInput().getBytes());
        return new BufferedReader(new InputStreamReader(inputStream));
    }
}



