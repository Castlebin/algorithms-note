package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 3. Longest Substring Without Repeating Characters
 *
 * Given a string, find the length of the longest substring without repeating characters.
 *
 * Examples:
 *
 * Given "abcabcbb", the answer is "abc", which the length is 3.
 *
 * Given "bbbbb", the answer is "b", with the length of 1.
 *
 * Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
public class N003 {
    public static void main(String[] args) {
        N003 n003 = new N003();
        System.out.println(n003.lengthOfLongestSubstring(null));
        System.out.println(n003.lengthOfLongestSubstring(""));
        System.out.println(n003.lengthOfLongestSubstring("a"));
        System.out.println(n003.lengthOfLongestSubstring("aa"));
        System.out.println(n003.lengthOfLongestSubstring("aaa"));
        System.out.println(n003.lengthOfLongestSubstring("abcd"));
        System.out.println(n003.lengthOfLongestSubstring("ababcabcdabcdeabcdef"));
        System.out.println(n003.lengthOfLongestSubstring("dvdf"));
    }

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int result = 0;
        Map<Character, Integer> addCharMap = new HashMap<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (!addCharMap.containsKey(chars[i])) {
                addCharMap.put(chars[i], i);
                if (addCharMap.size() > result) {
                    result = addCharMap.size();
                }
            } else {
                Integer du = addCharMap.get(chars[i]);
                addCharMap.keySet().removeIf(c -> addCharMap.get(c) <= du);
                addCharMap.put(chars[i], i);
            }
        }
        return result;
    }

    public int lengthOfLongestSubstring2(String s) {
        if (s.length() == 0) { return 0; }
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int max = 0;
        for (int i = 0, j = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                j = Math.max(j, map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i), i);
            max = Math.max(max, i - j + 1);
        }
        return max;
    }

}
