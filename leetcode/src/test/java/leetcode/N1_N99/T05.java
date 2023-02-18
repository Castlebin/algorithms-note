package leetcode.N1_N99;

import org.junit.Assert;
import org.junit.Test;


/**
 * 5. Longest Palindromic Substring
 * 5. 最长回文子串
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 */
public class T05 {

    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        String res = "";
        for (int index = 0; index < s.length() - 1; index++) {
            String r1 = longestPalindrome(s, index, index);
            String r2 = longestPalindrome(s, index, index + 1);
            if (r1.length() > res.length()) {
                res = r1;
            }
            if (r2.length() > res.length()) {
                res = r2;
            }
        }

        return res;
    }

    // 以 m1、m2 为中心，（m1 == m2 or m2 == m1 + 1）的最长回文子串
    // 中心往两边扩散
    private String longestPalindrome(String s, int m1, int m2) {
        while (m1 >= 0 && m2 < s.length() && s.charAt(m1) == s.charAt(m2)) {
            m1--;
            m2++;
        }
        return s.substring(m1 + 1, m2);
    }

    @Test
    public void test() {
        Assert.assertEquals("a", longestPalindrome("a", 0, 0));
        Assert.assertEquals("aa", longestPalindrome("aa", 0, 1));
        Assert.assertEquals("", longestPalindrome("ab", 0, 1));

        Assert.assertEquals("bb", longestPalindrome("cbbd"));
    }

}
