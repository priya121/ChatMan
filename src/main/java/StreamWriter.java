public interface StreamWriter {
    void writeBytes(String word);
    void writeObject(MessageList batch);
}
