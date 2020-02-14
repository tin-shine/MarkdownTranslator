package com.tinshine.MarkdownTranslator;

public class Item implements HtmlElem {

	@Override
    public String toHtml(String line) {
        StringBuilder ret = new StringBuilder();
        ret.append("<li>").append(line.substring(2)).append("</li>");
        return ret.toString();
    }

    @Override
    public String preProcess(String line) {
        line = new InlineCode().toHtml(line);
        return new Link().toHtml(line);
    }
}
