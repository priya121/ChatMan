public class ChatMan {
    private final IOConsole io;

    public ChatMan(IOConsole io) {
        this.io = io;
    }

    public String localAddress() {
        return io.getInput();
    }

    public int getPortNumber() {
        return Integer.parseInt(io.getInput());
    }
}
