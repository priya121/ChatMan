import org.junit.Test;

import java.io.Serializable;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MessageListTest implements Serializable {
    MessageList messageList = new MessageList();

    @Test
    public void isReadyIfItReceivesThreeMessages() {
        addThreeMessages(messageList);
        assertTrue(messageList.isReadyForSend());
    }

    @Test
    public void canGetMessageList() {
        addThreeMessages(messageList);
        assertEquals(Arrays.asList("hi", "hi", "hi"), messageList.getMessages());
    }

    @Test
    public void writesMessagesToOutputStream() {
        addThreeMessages(messageList);
    }

    private void addThreeMessages(MessageList messageList) {
        for (int i = 0; i < 3; i++) {
            messageList.addMessage("hi");
        }
    }
}
