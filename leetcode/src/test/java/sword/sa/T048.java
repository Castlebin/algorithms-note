package sword.sa;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 剑指 Offer 48. 最长不含重复字符的子字符串
  请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。

  示例1:
  输入: "abcabcbb"
  输出: 3
  解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。

  示例 2:
  输入: "bbbbb"
  输出: 1
  解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 */
public class T048 {

    public int lengthOfLongestSubstring(String s) {
        int maxLen = 0;
        int p1 = 0, p2 = 0;
        Map<Character, Integer> map = new HashMap<>();
        while (p2 < s.length()) {
            char c = s.charAt(p2);
            if (map.containsKey(c)) {
                if (map.size() > maxLen) {
                    maxLen = map.size();
                }
                Integer i = map.get(c);
                for (int index = p1; index <= i; index++) {
                    map.remove(s.charAt(index));
                }
                p1 = i + 1;
            }
            map.put(c, p2++);
        }
        return maxLen >= map.size() ? maxLen : map.size();
    }

    @Test
    public void test() {
        Assert.assertEquals(3, lengthOfLongestSubstring("abcabcbb"));
        Assert.assertEquals(1, lengthOfLongestSubstring("bbbbb"));
        Assert.assertEquals(3, lengthOfLongestSubstring("pwwkew"));
    }

}
