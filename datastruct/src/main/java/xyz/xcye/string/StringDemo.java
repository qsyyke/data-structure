package xyz.xcye.string;

/**
 * @author qsyyke
 * @date Created in 2022/7/9 22:09
 */


public class StringDemo {
    public static void main(String[] args) {
        String s =  "23abcrfabceasdfaswqejhsdab5csabsab5";
        String t = "ab5csabsab5";
        System.out.println(match(s, t));
    }

    private static int match(String str, String t) {
        int i = 0, j = 0;

        while (i < str.length() && j < t.length()) {
            if (str.charAt(i) == t.charAt(j)) {
                i++;
                j++;
            }else {
                // 退回到上次匹配的那个
                i = i - j + 1;
                j = 0;
            }
        }

        // 退出循环，其中一个或者两个的下标到达边界
        if (j == t.length()) {
            return i - j;
        }
        return 0;
    }
}
