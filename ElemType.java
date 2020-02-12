public class ElemType {
    static final int INVALID_TYPE = -1;
    static final int TITLE = 0;
    static final int ITEM = 1;
    static final int CODE = 2;

    public static int checkType(String line) {
        ElemType elemType = new ElemType();
        if (elemType.isTitle(line)) {
            return TITLE;
        } else if (elemType.isItem(line)) {
            return ITEM;
        } else if (elemType.isCode(line)) {
            return CODE;
        } else {
            return INVALID_TYPE;
        }
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
    
    public boolean isItem(String line) {
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