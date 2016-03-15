public class FakeStreamWriter implements StreamWriter {
    private String word = "";

    public FakeStreamWriter() {
    }

    @Override
    public void writeBytes(String word) {
        this.word += word;
    }

    public String getWrittenBytes() {
        return word;
    }
}
