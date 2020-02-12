

public class InlineCode implements HtmlElem {

    @Override
    public String preProcess(String line) {
        return null;
    }

    @Override
    public String toHtml(String line) {
        if (!line.contains("``")) {
            return line;
        }
        StringBuilder ret = new StringBuilder();
        int index = 0;
        while (index != -1 && index < line.length()) {
            int left = line.indexOf("``", index);
            int right = line.indexOf("``", left + 2);
            if (left == -1 || right == -1) break;
            ret.append(line.substring(index, left)).append("<code>")
                .append(line.substring(left + 2, right)).append("</code>");
            index = right + 2;
        }
        return ret.append(line.substring(index)).toString();
    }

}
