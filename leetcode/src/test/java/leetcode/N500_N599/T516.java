package leetcode.N500_N599;

import org.junit.Assert;
import org.junit.Test;

/**
 * 516. 最长回文子序列
 */
public class T516 {

    public int longestPalindromeSubseq(String s) {
        int N = s.length();
        // 定义 dp[i][j] 为 [i, j] 区间的最长回文子序列的长度
        int[][] dp = new int[N][N];
        // base case
        for (int i = 0; i < N; i++) { // 每个单个字符，都是一个回文子序列
            dp[i][i] = 1;
        }
        // dp[i][j] 仅仅跟 dp[i + 1][j - 1]、dp[i][j - 1]、dp[i + 1][j] 三个状态有关
        // 画出图形就知道应该选择怎么样合适的迭代方式了
        // 计算 dp[i][j]，必须保证 dp[i + 1][j - 1]、dp[i][j - 1]、dp[i + 1][j] 这三个都已经计算出来了
        for (int i = N - 2; i >= 0; i--) {
            for (int j = i + 1; j < N; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2; // +2 ，因为是左右两个字符
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]); // 看看那边的回文子序列更长
                }
            }
        }
        return dp[0][N - 1];
    }

    @Test
    public void test() {
        Assert.assertEquals(4, longestPalindromeSubseq("bbbab"));
        Assert.assertEquals(2, longestPalindromeSubseq("cbbd"));
    }

}
