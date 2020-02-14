package com.tinshine.MarkdownTranslator;

// import java.io.*;
/**
 * Compiler
 */
public class MainCompiler {

    public String transform(String inputText) {
        inputText = inputText.replace("\r\n", "\n");
        String[] lines = inputText.split("\n");
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
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
                    String codeSegment = segment.codeSegment(lines, i);
                    ret.append(transformByLine(segment, codeSegment) + "\n");
                    i = segment.lineCount(lines, i);
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
                    HtmlElem defaultElem = new Default();
                    line = defaultElem.preProcess(line);
                    ret.append(transformByLine(defaultElem, line));
                    break;
            }
        }
        return ret.toString();
    }

    private String transformByLine(HtmlElem htmlElem, String line) {
        return htmlElem.toHtml(line);
    }
}