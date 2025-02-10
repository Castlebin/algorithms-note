package leetcode.N600_N699;

import org.junit.Test;
import org.junit.Assert;

/**
 * 651. 4键键盘
 * 假设你有一个特殊的键盘，键盘上有如下键：
 * 键 1：（A）：在屏幕上打印一个 'A'。
 * 键 2：（CTRL-A）：选中整个屏幕。
 * 键 3：（CTRL-C）：复制选中区域到缓冲区。
 * 键 4：（CTRL-V）：将缓冲区内容输出到上次输入的结束位置，并显示在屏幕上。
 * 
 * 现在，你只可以按键 N 次（1 ≤ N ≤ 1000），请返回屏幕上最多可以显示多少个 'A'。
 */
public class T651 {
    public int maxA(int N) {
        int[] dp = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            // 先假设只按 A 键
            dp[i] = dp[i - 1] + 1;
            // 再假设是通过 Ctrl-A, Ctrl-C, Ctrl-V 操作 得到的结果
            for (int j = 3; j < i; j++) { 
                // 因为 Ctrl-A, Ctrl-C 需要 2 次操作，所以我们看的是 dp[j-2] 这个位置
                // 可以粘贴 dp[j - 2]  i - j 次 (加上原来屏幕上的，总数就是 i - j + 1 倍)
                // 所以 dp[i] = dp[j - 2] * (i - j + 1)
                dp[i] = Math.max(dp[i], dp[j - 2] * (i - j + 1));
            }
        }
        return dp[N];
    }


    @Test
    public void test() {
        Assert.assertEquals(3, maxA(3));
        Assert.assertEquals(9, maxA(7));
    }
    
}
