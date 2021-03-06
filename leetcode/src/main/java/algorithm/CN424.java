package algorithm;
/**
424. 替换后的最长重复字符

给你一个仅由大写英文字母组成的字符串，你可以将任意位置上的字符替换成另外的字符，总共可最多替换 k 次。
 在执行上述操作后，找到包含重复字母的最长子串的长度。

注意：字符串长度 和 k 不会超过 10^4。

示例 1：

输入：s = "ABAB", k = 2
输出：4
解释：用两个'A'替换为两个'B',反之亦然。

示例 2：

输入：s = "AABABBA", k = 1
输出：4
解释：
将中间的一个'A'替换为'B',字符串变为 "AABBBBA"。
子串 "BBBB" 有最长重复字母, 答案为 4。

 */
public class CN424 {

    /**
     * 此题还是一个滑动窗口的题
     */
    public int characterReplacement(String str, int k) {
        int left = 0 ,right = 0;
        char[] map = new char[26];
        int maxSame = 0;
        while (right < str.length()) {
            char c = str.charAt(right);
            map[c - 'A']++;
            maxSame = Math.max(maxSame, map[c - 'A']);
            if (maxSame + k < right - left + 1) {
                map[str.charAt(left) - 'A']--;
                left++;
            }
            right++;
        }
        return right - left;
    }

}
