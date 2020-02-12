

public class Item implements HtmlElem {

    public static boolean nextItem = false;

	@Override
    public String toHtml(String line) {
        StringBuilder ret = new StringBuilder();
        if (!Item.nextItem) {
            ret.append("<ul>");
        }
        ret.append("<li>").append(line.substring(2)).append("</li>");
        if (!Item.nextItem) {
            ret.append("</ul>");
        }
        Item.nextItem = true;
        return ret.toString();
    }

    @Override
    public String preProcess(String line) {
        return new InlineCode().toHtml(line);
    }
}
