package com.tinshine.MarkdownTranslator;

public class Image implements HtmlElem {

    @Override
    public String toHtml(String line) {
        StringBuilder ret = new StringBuilder();
        ret.append("<img ");
        int cnt = 2;
        while (line.charAt(cnt) != ']') cnt++;
        String alt = line.substring(2, cnt++);
        int start = ++cnt;
        while (line.charAt(cnt) != ')') cnt++;
        String src = line.substring(start, cnt);
        ret.append("src=\"").append(src).append("\" alt=\"").append(alt).append("\">");
        return ret.toString();
    }

    @Override
    public String preProcess(String line) {
        return new InlineCode().toHtml(line);
    }

}
