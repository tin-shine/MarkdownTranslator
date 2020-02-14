package com.tinshine.MarkdownTranslator;

public class Link implements HtmlElem {

    @Override
    public String preProcess(String line) {
        return line;
    }

    @Override
    public String toHtml(String line) {
        int index = 0;
        StringBuilder ret = new StringBuilder();
        if (!line.contains("](")) return line;
        while (index < line.length()) {
            int begin = index;
            while (line.charAt(index) != '[') {
                if (index >= line.length() - 3) return ret.append(line.substring(begin)).toString();
                index++;
            }
            int left = index;
            while (line.charAt(index) != ']') {
                if (index >= line.length() - 2) return ret.append(line.substring(begin)).toString();
                index++;
            }
            int right = index;
            if (line.charAt(++index) == '(') {
                int linkLeft = index;
                while (line.charAt(index) != ')') {
                    if (index >= line.length() - 1) return ret.append(line.substring(begin)).toString();
                    index++;
                }
                int linkRight = index;
                ret.append(line.substring(begin, left)).append("<a href=\"").append(line.substring(linkLeft + 1, linkRight))
                    .append("\">").append(line.substring(left + 1, right)).append("</a>");
            }
            index++;
        }
        return ret.toString();
    }

}
