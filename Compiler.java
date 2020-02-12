import java.io.*;
/**
 * Compiler
 */
public class Compiler {

    public String transform(String[] inputString) throws IOException {
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < inputString.length; i++) {
            String line = inputString[i];
            if (line == null || line.length() == 0) continue;
            switch (ElemType.checkType(line)) {
                case ElemType.TITLE:
                    Item.itemNum = 1;
                    ret.append(transformByLine(new Title(), line) + "\n");
                    break;
                case ElemType.ITEM:
                    ret.append(transformByLine(new Item(), line) + "\n");
                    break;
                case ElemType.CODE:
                    Code segment = new Code();
                    String codeSegment = segment.codeSegment(inputString, i);
                    ret.append(transformByLine(segment, codeSegment) + "\n");
                    i = segment.lineCount(inputString, i);
                    break;
                default:
                    break;
            }
        }
        return ret.toString();
    }

	private String transformByLine(HtmlElem htmlElem, String line) {
        return htmlElem.toHtml(line);
    }

    public static String[] readString(String inputFile) throws IOException {
		FileInputStream inputStream = new FileInputStream(new File(inputFile));
        BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder inputText = new StringBuilder();
        String line = null;
        while ((line = bf.readLine()) != null) {
            inputText.append(line + "\n");
        }
        bf.close();
        return inputText.toString().split("\n");
	}

	public static void writeString(String outputFile, String outputText) throws IOException {
        PrintWriter pw = new PrintWriter(new File(outputFile));
        pw.println(outputText);
        pw.close();
	}
}