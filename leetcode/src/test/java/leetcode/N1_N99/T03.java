package leetcode.N1_N99;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

/**
 * 3. 无重复字符的最长子串
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 */
public class T03 {
    /** 典型的滑动窗口解法 */
    public int lengthOfLongestSubstring(String s) {
        // 滑动窗口的左右指针
        int left = 0, right = 0;
        // 保存当前窗口中的字符
        Set<Character> window = new HashSet<>();
        int N = s.length();
        int result = 0;
        while (right < N) {
            char c = s.charAt(right);
            // 如果窗口中已经有了这个字符，就一直移动左指针，直到窗口中没有这个字符。这样才能放入
            while (window.contains(c)) {
                window.remove(s.charAt(left));
                left++;
            }
            window.add(c);
            result = Math.max(result, window.size());
            right++;
        }
        return result;
    }

    @Test
    public void test() {
        Assert.assertEquals(3, lengthOfLongestSubstring("abcabcbb"));
        Assert.assertEquals(1, lengthOfLongestSubstring("bbbbb"));
        Assert.assertEquals(3, lengthOfLongestSubstring("pwwkew"));
    }
}
