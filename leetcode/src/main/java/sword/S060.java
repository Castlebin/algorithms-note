package sword;

import org.junit.Test;

import java.util.Arrays;

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
     * 使用两个数组，保存上一轮、本轮，每个可能点数的次数，要概率的话，除以总可能次数即可
     *
     * arr[i] 表示 和为点数 i 的骰子组合次数
     *
     * 此方法容易理解，计算简单
     */

    // 表示 每个骰子 有 1-6 这几个值
    public static final int DICE_FACES = 6;

    public double[] probability(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("n must >= 1");
        }

        int maxSum = DICE_FACES * n;
        int[] probabilities = new int[maxSum + 1];
        for (int i = 1; i <= DICE_FACES; i++) {
            probabilities[i] = 1;
        }
        if (n > 1) {
            // 为了节约频繁申请数组的开销，所以申请了两个数组，重复使用。注意处理
            int[] lastProbabilities = new int[maxSum + 1];
            for (int diceCount = 2; diceCount <= n; diceCount++) {
                int[] tmp = lastProbabilities;
                lastProbabilities = probabilities;
                probabilities = tmp;
                // 注意处理上一轮遗留的计数
                probabilities[diceCount - 2] = 0;
                probabilities[diceCount - 1] = 0;
                int maxNum = diceCount * DICE_FACES;
                for (int index = diceCount; index <= maxNum; index++) {
                    // 记住要先重置为0
                    probabilities[index] = 0;
                    for (int face = 1; face <= DICE_FACES && index - face >= 0; face++) {
                        probabilities[index] += lastProbabilities[index - face];
                    }
                }
            }
        }
        double total = Math.pow(6, n);

        double[] p = new double[5 * n + 1];
        for(int i = n,t = 0; i < probabilities.length; i++) {
            p[t++] = probabilities[i] / total;
        }

        return p;

        // 下面的lambda 表达式很慢，用循环好了
        /*
        return Arrays.stream(probabilities)
                .filter( x -> x > 0.0 )
                .mapToDouble( x -> x / total)
                .toArray();*/
    }


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
        System.out.println(Arrays.toString(probability(1)));
        System.out.println(Arrays.toString(probability(2)));
        System.out.println(Arrays.toString(probability(3)));
    }

}
