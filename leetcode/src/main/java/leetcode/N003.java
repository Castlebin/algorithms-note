package leetcode;

import java.util.*;

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
            if ( !addCharMap.containsKey(chars[i]) ) {
                addCharMap.put(chars[i], i);
                if (addCharMap.size() > result) {
                    result = addCharMap.size();
                }
            } else {
                Integer du = addCharMap.get(chars[i]);
                Iterator<Character> iterator = addCharMap.keySet().iterator();
                while (iterator.hasNext()) {
                    if (addCharMap.get(iterator.next()) <= du) {
                        iterator.remove();
                    }
                }
                addCharMap.put(chars[i], i);
            }
        }
        return result;
    }

}
