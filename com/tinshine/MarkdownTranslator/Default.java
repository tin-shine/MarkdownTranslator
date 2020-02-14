package com.tinshine.MarkdownTranslator;

public class Default implements HtmlElem {

    @Override
    public String preProcess(String line) {
        line = new InlineCode().toHtml(line);
        return line;
    }

    @Override
    public String toHtml(String line) {
        StringBuilder ret = new StringBuilder();
        line = new InlineCode().toHtml(line);
        line = new Link().toHtml(line);
        ret.append("<p>").append(line).append("</p>");
        return ret.toString();
    }

}
