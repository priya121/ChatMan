public class MessageListFactory {
    private final IOConsole io;

    public MessageListFactory(IOConsole io) {
        this.io = io;
    }

    public MessageList create() {
        MessageList list = new MessageList();
        int counter = 0;
        while (counter < 3) {
            String message = io.getInput();
            list.addMessage(message);
            counter++;
        }
        return list;
    }
}
