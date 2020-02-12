public class ElemType {
    static final int INVALID_TYPE = -1;
    static final int TITLE = 0;
    static final int ITEM_LIST = 1;
    static final int CODE = 2;
    static final int IMAGE = 3;
    static final int ITEM = 4;

    public static int checkType(String line) {
        ElemType elemType = new ElemType();
        if (elemType.isTitle(line)) {
            return TITLE;
        } else if (elemType.isItemList(line)) {
            return ITEM_LIST;
        } else if (elemType.isCode(line)) {
            return CODE;
        } else if (elemType.isImage(line)) {
            return IMAGE;
        } else if (elemType.isItem(line)) {
            return ITEM;
        } else {
            return INVALID_TYPE;
        }
    }

    private boolean isItem(String line) {
        return line.startsWith("* ") && line.length() > 2;
    }

    private boolean isImage(String line) {
        if (line.startsWith("![")) {
            int cnt = 2;
            while (cnt < line.length() && line.charAt(cnt) != ']') {
                cnt++;
            }
            if (cnt < line.length() - 1 && line.charAt(++cnt) == '(') {
                while (cnt < line.length() && line.charAt(cnt) != ')') {
                    cnt++;
                }
                if (cnt < line.length()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isTitle(String line) {
        int cnt = 0;
        while (line.charAt(cnt) == '#') cnt++;
        if (cnt != 0 && line.charAt(cnt) == ' ') {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean isItemList(String line) {
        int cnt = 0;
        while (line.charAt(cnt) >= '1' && line.charAt(cnt) <= '9') cnt++;
        if (cnt != 0 && cnt <= line.length() - 2 && 
            line.charAt(cnt) == '.' && line.charAt(cnt + 1) == ' ') {
                return true;
        } else {
            return false;
        }
    }
    
    public boolean isCode(String line) {
        return line.startsWith("```");
    }
}