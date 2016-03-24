import org.junit.Ignore;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class ServerTest {
    ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(recordedOutput);

    @Test
    public void displaysThreeLinesOfMessagesSentFromClient() {
        UserIO console = convertUserInput(new ByteArrayInputStream("".getBytes()));
        ConnectionSocket fakeSocket = new FakeSocketSpy(console);
        ChatServer server = new ChatServer(fakeSocket, console);
        server.showAllMessages("Hi\nHow are you?\nI'm good.\n");
    }

    @Ignore
    @Test
    public void displaysOutWordsSentFromClientSocket() throws IOException {
        UserIO console = convertUserInput(new ByteArrayInputStream("localhost\n4444\nHi\nHi how are you?\nGood\nyeah\nok\nquit\nquit\nquit\nquit\n".getBytes()));
        ConnectionSocket fakeSocket = new FakeSocketSpy(console);
        ChatServer server = new ChatServer(fakeSocket, console);
        server.sendMessagesToClient();
        assertEquals("Hi\n", recordedOutput.toString());
    }

    public UserIO convertUserInput(InputStream userInput) {
        return new UserIO(userInput, output);
    }
}