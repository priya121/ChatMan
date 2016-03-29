public class FakeStreamWriter implements StreamWriter {
    private String word = "";

    public FakeStreamWriter() {
    }

    @Override
    public void writeBytes(String word) {
        this.word += word;
    }

    @Override
    public void writeObject(MessageList batch) {
        this.word += batch;
    }

    public String getWrittenBytes() {
        return word;
    }
}
