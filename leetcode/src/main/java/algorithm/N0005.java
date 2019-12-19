package algorithm;

/**
 * 5. Longest Palindromic Substring
 * <p>
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
 * <p>
 * Example 1:
 * <p>
 * Input: "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 * Example 2:
 * <p>
 * Input: "cbbd"
 * Output: "bb"
 */
public class N0005 {

    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        String result = s.substring(0, 1);
        for (int i = 0; i < s.length(); i++) {
            // 1. 回文串长度为单数的情况
            String r1 = longestPalindrome(result, s, i - 1, i + 1);
            if (r1.length() > result.length()) {
                result = r1;
            }
            // 2. 回文串长度为双数的情况
            String r2 = longestPalindrome(result, s, i - 1, i);
            if (r2.length() > result.length()) {
                result = r2;
            }
        }
        return result;
    }

    private String longestPalindrome(String result, String s, int begin, int end) {
        while (begin >= 0 && end < s.length() && s.charAt(begin) == s.charAt(end)) {
            result = s.substring(begin, end + 1);
            begin--;
            end++;
        }
        return result;
    }

}
