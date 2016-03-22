import org.junit.Test;

import java.io.*;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class ServerTest {
    IOConsole fakeInput = new FakeIO(Arrays.asList("Hi", "How", "Are", "you", "?","?"));
    ConnectionSocket fakeSocket = new FakeSocketSpy(fakeInput);
    ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(recordedOutput);

    @Test
    public void displaysOutWordsSentFromClientSocket() throws IOException {
        EchoConsole console = convertUserInput(new ByteArrayInputStream("Hi\nHi how are you?".getBytes()));
        ChatServer server = new ChatServer(fakeSocket, console);
        server.sendMessagesToClient();
        assertEquals("Hi\n", recordedOutput.toString());
    }

    @Test
    public void storesMessageHistory() throws IOException {
        ChatServer server = new ChatServer(fakeSocket, fakeInput);
        server.sendMessagesToClient();
        server.sendMessagesToClient();
        assertEquals("Hi", ChatServer.messageHistory.get(0));
        assertEquals("Are", ChatServer.messageHistory.get(1));
    }

    @Test
    public void receivesAGroupOfThreeMessagesFromClient() {

    }

    public EchoConsole convertUserInput(InputStream userInput) {
        return new EchoConsole(userInput, output);
    }
}