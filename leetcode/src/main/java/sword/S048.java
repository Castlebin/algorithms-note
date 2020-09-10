package sword;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 剑指 Offer 48. 最长不含重复字符的子字符串

 请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
 */
public class S048 {

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        Set<Character> chars = new HashSet<>();
        int maxSubLength = 0;
        for (int p1 = 0, p2 = 0; p2 < s.length(); p2++) {
            char c = s.charAt(p2);
            if (!chars.contains(c)) {
                chars.add(c);
                if (chars.size() > maxSubLength) {
                    maxSubLength = chars.size();
                }
            } else {
                int cIndex = s.indexOf(c, p1);
                for (int i = p1; i < cIndex; i++) {
                    chars.remove(s.charAt(i));
                }
                p1 = cIndex + 1;
            }
        }
        return maxSubLength;
    }

    @Test
    public void test() {
    //    Assert.assertEquals(2, lengthOfLongestSubstring("aab"));
        Assert.assertEquals(3, lengthOfLongestSubstring("pwwkew"));
    }

}
