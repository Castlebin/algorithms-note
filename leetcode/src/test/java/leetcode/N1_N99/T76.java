package leetcode.N1_N99;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

/**
 * 76. 最小覆盖子串
 */
public class T76 {

    /**
     * 滑动窗口的思路：
     * 1. 首先，找到一个符合条件的窗口
     * 2. 再看看这个窗口可不可以缩小
     * 重复以上两个步骤，直到窗口右边界已经不能再移动
     */
    public String minWindow(String s, String t) {
        // needs 记录需要的字符和对应个数
        Map<Character, Integer> needs = new HashMap<>();
        for (char c : t.toCharArray()) {
            needs.put(c, needs.getOrDefault(c, 0) + 1);
        }
        // window 中保存当前窗口中是需要的字符及对应个数
        Map<Character, Integer> window = new HashMap<>();
        // valid 保存当前窗口中已经有几个字符满足了需要了
        int valid = 0;
        // [left、right] 就是当前窗口
        int left = 0, right = 0;
        // start 保存符合要求的子串的起始位置，len 保存其长度。len 初始化为 Integer.MAX_VALUE 表示当前还没找到合适的解
        int start = 0, len = Integer.MAX_VALUE;
        while (right < s.length()) {
            // c 当前需要移入窗口的字符
            char c = s.charAt(right);
            right++;
            if (needs.containsKey(c)) { // 说明是一个需要的字符
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).equals(needs.get(c))) { // 又找到了一个满足了要求的字符
                    valid++;
                    // 如果全部字符都已经满足了  (这个 while 块也可以直接移到外层的 while 里)
                    while (valid == needs.size()) { // 注意，这里需要是 while，因为可能需要多次缩小窗口.也就是多次移动左边界
                        if (right - left < len) { // 比上一个解更短
                            start = left;
                            len = right - left;
                        }
                        // 增加左边界，缩小窗口
                        char d = s.charAt(left); // 要从窗口中移除的字符
                        left++;
                        if (needs.containsKey(d)) {
                            if (window.get(d).equals(needs.get(d))) {
                                valid--; // 符合条件的字符少了一个
                            }
                            window.put(d, window.get(d) - 1);
                        }
                    }
                }
            }
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }

    @Test
    public void test() {
        Assert.assertEquals("BANC", minWindow("ADOBECODEBANC", "ABC"));
        Assert.assertEquals("a", minWindow("a", "a"));
        Assert.assertEquals("", minWindow("a", "aa"));
    }

}
