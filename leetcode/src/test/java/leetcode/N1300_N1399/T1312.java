package leetcode.N1300_N1399;

import org.junit.Test;
import org.junit.Assert;

/**
 * 1312.让字符串成为回文串的最少插入次数
 */
public class T1312 {

    public int minInsertions(String s) {
        int N = s.length();
        // 定义 dp[i][j] 为 让[i, j] 这部分子字符串成为回文串的最少插入次数
        int[][] dp = new int[N][N];
        // base case 是 dp[i][i] 都为 0。所以不用做什么
        // dp[i][j] 仅跟 dp[i + 1][j]、dp[i][j - 1]、dp[i + 1][j - 1] 这些状态有关
        // 所以，在计算 dp[i][j] 之前，必须保证 dp[i + 1][j]、dp[i][j - 1]、dp[i + 1][j - 1] 都已经计算出来了
        // 采用 从下到上、从左到右 的遍历方式
        for (int i = N - 2; i >= 0; i--) {
            for (int j = i + 1; j < N; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    // 两边字符是相等的，所以 dp[i][j] 直接就等于 dp[i + 1][j - 1]，不用额外付出工作
                    dp[i][j] = dp[i + 1][j - 1];
                } else {
                    dp[i][j] = min(
                            dp[i + 1][j] + 1,
                            dp[i][j - 1] + 1,
                            dp[i + 1][j - 1] + 2 // 实际上跟它无关，它不会是这三个数里面最小的。方便起见，直接这么写，懒得去证明
                    );
                }
            }
        }
        return dp[0][N - 1];
    }

    private int min(int... arr) {
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        return min;
    }

    @Test
    public void test() {
        Assert.assertEquals(0, minInsertions("zzazz"));
        Assert.assertEquals(2, minInsertions("mbadm"));
        Assert.assertEquals(5, minInsertions("leetcode"));
    }

}
