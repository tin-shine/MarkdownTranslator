

public class Title implements HtmlElem {

    @Override
	public String toHtml(String line) {
        int level = 0;
        StringBuilder ret = new StringBuilder();
        while (line.charAt(level) == '#') level++;
        ret.append("<h" + level + ">");
        int index = level;
        while (line.charAt(index) == ' ') index++;
        ret.append(line.substring(index));
		return ret.append("</h" + level + ">").toString();
	}

}
