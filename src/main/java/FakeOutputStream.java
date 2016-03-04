public class FakeOutputStream implements DataOutput {
    private String word = "";

    public FakeOutputStream() {
    }

    @Override
    public void writeBytes(String word) {
        this.word += word;
    }

    public String getWrittenBytes() {
        return word;
    }
}
