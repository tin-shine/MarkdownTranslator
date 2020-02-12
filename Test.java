import java.io.*;

/**
 * Test
 */
public class Test {

    public static void main(String[] args) throws IOException {
        String[] inputText = Compiler.readString("input.md");
        String outputText = new Compiler().transform(inputText);
        Compiler.writeString("output.html", outputText);
    }
}