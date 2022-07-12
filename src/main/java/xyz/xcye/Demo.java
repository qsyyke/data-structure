package xyz.xcye;

import java.util.regex.Pattern;

/**
 * @author qsyyke
 * @date Created in 2022/6/29 23:10
 */


public class Demo {
    public static void main(String[] args) {
    }

    public String replaceSpace(String s) {
        if (s == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            byte charAt = (byte) s.charAt(i);
            if (charAt == (byte) ' ') {
                builder.append("%20");
            }else {
                builder.append(s.charAt(i));
            }
        }
        return builder.toString();
    }
}
