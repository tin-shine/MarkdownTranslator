package com.tinshine.MarkdownTranslator;

public class ItemList implements HtmlElem {

	public static int itemNum = 1;

    @Override
	public String toHtml(String line) {
        StringBuilder ret = new StringBuilder();
        ret.append("<ol start=\"" + itemNum + "\">");
        itemNum++;
        int cnt = 0;
        while (line.charAt(cnt) >= '1' && line.charAt(cnt) <= '9') cnt++;
        cnt += 2;
        ret.append("<li>").append(line.substring(cnt)).append("</li></ol>");
		return ret.toString();
	}

    @Override
    public String preProcess(String line) {
        line = new InlineCode().toHtml(line);
        return new Link().toHtml(line);
    }

}
