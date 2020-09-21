package sword;

import org.junit.Test;


/**
 剑指 Offer 60. n个骰子的点数
 把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。

 你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。

 示例 1:
 输入: 1
 输出: [0.16667,0.16667,0.16667,0.16667,0.16667,0.16667]

 示例 2:
 输入: 2
 输出: [0.02778,0.05556,0.08333,0.11111,0.13889,0.16667,0.13889,0.11111,0.08333,0.05556,0.02778]
*/
public class S060 {

    /**
     * todo
     */
    public double[] twoSum(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("n must >= 1");
        }
        double[] dp = new double[]{1/6d,1/6d,1/6d,1/6d,1/6d,1/6d};
        for (int c = 2; c <= n; c++) {
            double[] nextDp = new double[5 * c + 1];
            for (int i = 0; i < dp.length; i++) {
                for (int t = 1; t <= 6; t++) {
                    nextDp[i + t - 1] += dp[i] / 6;
                }
            }
            dp = nextDp;
        }
        return dp;
    }

    @Test
    public void test() {

    }

}
