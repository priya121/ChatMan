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
        ServerReadInThread server = new ServerReadInThread(fakeSocket, console);
        server.sendMessagesToClient();
        assertEquals("Hi\n", recordedOutput.toString());
    }

    @Test
    public void storesMessageHistory() throws IOException {
        ServerReadInThread server = new ServerReadInThread(fakeSocket, fakeInput);
        server.sendMessagesToClient();
        server.sendMessagesToClient();
        assertEquals("Hi", ServerReadInThread.messageHistory.get(0));
        assertEquals("Are", ServerReadInThread.messageHistory.get(1));
    }

    public EchoConsole convertUserInput(InputStream userInput) {
        return new EchoConsole(userInput, output);
    }
}