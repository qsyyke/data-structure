package xyz.xcye.string;

import java.util.Arrays;

/**
 * @author qsyyke
 * @date Created in 2022/7/9 22:45
 */


public class StringMKPDemo {
    public static void main(String[] args) {
        String s =  "23abcrfabceasdfaswqejhsdab5csabsab5";
        String t = "ab5csabsab5";
        //          00000120113
        //for (int i : kmpNext(t)) {
        //    System.out.print(i + "");
        //}
        System.out.println(kmpString(s, t));
    }

    private static int[] kmpNext(String dest) {
        int[] next = new int[dest.length()];
        next[0] = 0;

        for (int i = 1, j = 0; i < dest.length(); i++) {
            while (j > 0 && dest.charAt(j) != dest.charAt(i)) {
                j = next[j - 1];
            }
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }

    private static int kmpString(String str, String match) {
        int[] next = kmpNext(match);
        for (int i = 0, j = 0; i < str.length(); i++) {
            while (j > 0 && str.charAt(i) != match.charAt(j)) {
                j = next[j - 1];
            }
            if (str.charAt(i) == match.charAt(j)) {
                j++;
            }
            if (j == match.length()) {
                return i - j + 1;
            }
        }
        return 0;
    }
}
