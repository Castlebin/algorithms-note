package leetcode.N1_N99;

import org.junit.Assert;
import org.junit.Test;

/**
 * 72. 编辑距离
 */
public class T72 {

    /**
     * 使用 动态规划 求解。定义 dp[i][j] word1 到第 i 个字符时、word2 到第 j 个字符时 的编辑距离
     */
    public int minDistance(String word1, String word2) {
        int M = word1.length();
        int N = word2.length();
        int[][] dp = new int[M + 1][N + 1];

        // 初始化 base case
        for (int i = 0; i <= M; i++) {
            dp[i][0] = i;  // 将 word1 转换为空字符串需要删除 i 个字符
        }
        for (int j = 0; j <= N; j++) {
            dp[0][j] = j;  // 将空字符串转换为 word2 需要插入 j 个字符
        }
        
        // 计算 dp
        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= N; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // 替换、删除、插入 三种操作中，取最小值
                    dp[i][j] = min(
                        dp[i - 1][j], dp[i][j - 1],  // 删除或插入
                        dp[i - 1][j - 1]  // 替换
                    ) + 1;
                }
            }
        }
        return dp[M][N];
    }

    public int min(int... arr) {
        int min = arr[0];
        for(int index = 1; index < arr.length; index++) {
            if(min > arr[index]) {
                min = arr[index];
            }
        }
        return min;
    }

    @Test
    public void test() {
        Assert.assertEquals(3, minDistance("horse", "ros"));
        Assert.assertEquals(5, minDistance("intention", "execution"));
    }

}
