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
                    HtmlElem title = new Title();
                    line = title.preProcess(line);
                    ret.append(transformByLine(title, line) + "\n");
                    break;
                case ElemType.ITEM_LIST:
                    HtmlElem itemList = new ItemList();
                    line = itemList.preProcess(line);
                    ret.append(transformByLine(itemList, line) + "\n");
                    break;
                case ElemType.CODE:
                    Code segment = new Code();
                    line = segment.preProcess(line);
                    String codeSegment = segment.codeSegment(inputString, i);
                    ret.append(transformByLine(segment, codeSegment) + "\n");
                    i = segment.lineCount(inputString, i);
                    break;
                case ElemType.IMAGE:
                    HtmlElem image = new Image();
                    line = image.preProcess(line);
                    ret.append(transformByLine(image, line) + "\n");
                    break;
                case ElemType.ITEM:
                    HtmlElem item = new Item();
                    line = item.preProcess(line);
                    ret.append(transformByLine(item, line) + "\n");
                    break;
                default:
                    Item.nextItem = false;
                    line = new InlineCode().toHtml(line);
                    ret.append("<p>").append(line).append("</p>");
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