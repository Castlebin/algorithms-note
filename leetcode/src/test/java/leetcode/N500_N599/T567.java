package leetcode.N500_N599;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

/**
 * 567. 字符串的排列
 * https://leetcode-cn.com/problems/permutation-in-string/
 */
public class T567 {

    /** 典型的滑动窗口解法 */
    public boolean checkInclusion(String s1, String s2) {
        int M = s1.length();
        int N = s2.length();
        if (M > N) {
            return false;
        }
        Map<Character, Integer> needs = new HashMap<>();
        for (char c : s1.toCharArray()) {
            needs.put(c, needs.getOrDefault(c, 0) + 1);
        }
        Map<Character, Integer> window = new HashMap<>();
        int valid = 0;
        int left = 0, right = 0;
        while (right < N) {
            char c = s2.charAt(right);
            if (needs.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).equals(needs.get(c))) {
                    valid++;
                }
            }
            while (valid == needs.size()) {
                if (right + 1 - left == M) {
                    return true;
                }
                // 增加 left ，缩小窗口
                char lc = s2.charAt(left);
                if (needs.containsKey(lc)) {
                    int count = window.get(lc);
                    if (count == needs.get(lc)) {
                        valid--;
                    }
                    window.put(lc, count - 1);
                }
                left++;
            }
            // 增大窗口
            right++;
        }
        return false;
    }

    @Test
    public void test() {
        Assert.assertTrue(checkInclusion("ab", "eidbaooo"));
        Assert.assertFalse(checkInclusion("ab", "eidboaoo"));
    }
}
