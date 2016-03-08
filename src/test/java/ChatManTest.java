import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class ChatManTest {

    @Test
    public void getsLocalAddressFromUser() {
        IOConsole fakeIO = new FakeIO(Arrays.asList("localhost"));
        ChatMan newChat = new ChatMan(fakeIO);
        assertEquals("localhost", newChat.localAddress());
    }

    @Test
    public void getsPortNumberFromUser() {
        IOConsole fakeIO = new FakeIO(Arrays.asList("4444"));
        ChatMan newChat = new ChatMan(fakeIO);
        assertEquals(4444, newChat.getPortNumber());
    }
}
