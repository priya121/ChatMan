import java.io.*;

public class EchoConsole implements IOConsole {
    private final BufferedReader input;
    private final PrintStream output;

    public EchoConsole(InputStream input, PrintStream output) {
        this.input = new BufferedReader(new InputStreamReader(input));
        this.output = output;
    }

    public String getInput() {
        try {
            return input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String showOutput(String message) {
        output.println(message);
        return null;
    }
}
