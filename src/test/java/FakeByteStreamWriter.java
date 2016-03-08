public class FakeByteStreamWriter implements BytesToStreamWriter {
    private String word = "";

    public FakeByteStreamWriter() {
    }

    @Override
    public void writeBytes(String word) {
        this.word += word;
    }

    public String getWrittenBytes() {
        return word;
    }
}
