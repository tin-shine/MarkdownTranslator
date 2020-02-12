

public class Code implements HtmlElem {

    @Override
    public String toHtml(String line) {
        StringBuilder ret = new StringBuilder();
        ret.append("<code><pre>").append(line).append("</pre></code>");
        return ret.toString();
    }

    @Override
    public String preProcess(String line) {
        Item.nextItem = false;
        return line;
    }

	public int lineCount(String[] inputString, int i) {
        while (++i < inputString.length) {
            if (inputString[i].startsWith("```")) {
                return i;
            }
        }
		return 0;
	}

	public String codeSegment(String[] inputString, int i) {
        StringBuilder ret = new StringBuilder();
        while (++i < inputString.length) {
            if (inputString[i].startsWith("```")) {
                break;
            } else {
                ret.append(inputString[i] + "\n");
            }
        }
		return ret.toString();
	}

}
