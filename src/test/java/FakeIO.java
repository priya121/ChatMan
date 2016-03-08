import java.util.LinkedList;
import java.util.List;

public class FakeIO implements IOConsole {
    private LinkedList<String> words;

    public FakeIO(List<String> words) {
        this.words = new LinkedList<>(words);
    }

    @Override
    public String getInput() {
        return words.pop();
    }

    @Override
    public String showOutput(String message) {
        return message;
    }
}
