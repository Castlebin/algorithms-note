package leetcode.N1100_N1199;

import org.junit.Assert;
import org.junit.Test;

/**
 * 1143. 最长公共子序列
 */
public class T1143 {
    /**
     * 动态规划，定义 dp[i][j] 为到 text1 的第 i 个字符、text2 的第 j 个字符时，最长的公共子串是多长
     */
    public int longestCommonSubsequence(String text1, String text2) {
        char[] t1 = text1.toCharArray();
        char[] t2 = text2.toCharArray();
        int[][] dp = new int[t1.length + 1][t2.length + 1]; // 为了方便计算而已，避免边界条件需要特殊处理
        for (int i = 1; i <= t1.length; i++) {
            for (int j = 1; j <= t2.length; j++) {
                // 多找到了一个公共字符
                if (t1[i - 1] == t2[j - 1]) { // 注意位置  i - 1 坐标代表的是第 i 个字符 （序号从 1 开始，注意 dp[i][j] 的定义）
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {// 没找到新的公共字符，所以取前面两个解中的较大解
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[t1.length][t2.length];
    }

    @Test
    public void test() {
        Assert.assertEquals(3, longestCommonSubsequence("abcde", "ace"));
        Assert.assertEquals(3, longestCommonSubsequence("abc", "abc"));
        Assert.assertEquals(0, longestCommonSubsequence("abc", "def"));
    }

}
