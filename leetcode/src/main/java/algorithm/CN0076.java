package algorithm;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedHashMap;

/**
 76. 最小覆盖子串
 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。

 注意：如果 s 中存在这样的子串，我们保证它是唯一的答案。

 示例 1：

 输入：s = "ADOBECODEBANC", t = "ABC"
 输出："BANC"
 示例 2：

 输入：s = "a", t = "a"
 输出："a"


 提示：

 1 <= s.length, t.length <= 105
 s 和 t 由英文字母组成


 进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/minimum-window-substring
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 */
public class CN0076 {

    /**
     * 滑动窗口
     */
    public String minWindow(String str, String target) {
        int left = 0, right = 0;
        LinkedHashMap<Character, Integer> window = new LinkedHashMap<>();
        LinkedHashMap<Character, Integer> needs = new LinkedHashMap<>();
        for (char c : target.toCharArray()) {
            needs.put(c, needs.getOrDefault(c, 0) + 1);
        }
        if (target.equals("")) {
            return "";
        }

        String minWindow = "";
        // 开始滑动窗口算法
        while (right < str.length()) {
            char rightChar = str.charAt(right);
            window.put(rightChar, window.getOrDefault(rightChar, 0) + 1);

            // 如果窗口中已经包含了所有的字符，可能需要从左边对窗口进行缩小
            while (containsAll(window, needs)) {
                if (right - left + 1 < minWindow.length() || minWindow.equals("")) {
                    minWindow = str.substring(left, right + 1);
                }

                // 缩小窗口
                char leftChar = str.charAt(left);
                if (window.get(leftChar) > 1) {
                    window.put(leftChar, window.get(leftChar) - 1);
                } else {
                    window.remove(leftChar);
                }
                left++;
            }

            right++;
        }

        return minWindow;
    }

    public boolean containsAll(LinkedHashMap<Character, Integer> window,
                               LinkedHashMap<Character, Integer> needs) {
        for (Character c : needs.keySet()) {
            if (!window.containsKey(c) || window.get(c) < needs.get(c)) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void testMinWindow() {
        Assert.assertEquals("BANC", minWindow("ADOBECODEBANC", "ABC"));
    }

}
