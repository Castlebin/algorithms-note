package leetcode.N300_N399;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;


/**
 * 322. Coin Change
 * 322. 零钱兑换
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 * 你可以认为每种硬币的数量是无限的。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/coin-change
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class T322 {

    public int coinChange(int[] coins, int amount) {
        int[] memo = new int[amount + 1];
        // 初始化值，不可能取到
        Arrays.fill(memo, Integer.MAX_VALUE);
        return coinChange(coins, amount, memo);
    }

    public int coinChange(int[] coins, int amount, int[] memo) {
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1; // 说明无解
        }
        // 如果已经计算过了
        if (memo[amount] != Integer.MAX_VALUE) {
            return memo[amount];
        }
        // 递归求解
        for (int coin : coins) {
            // 子问题求解
            int sub = coinChange(coins, amount - coin, memo);
            if (sub >= 0) {
                memo[amount] = Math.min(memo[amount], sub + 1);
            }
        }
        // 如果这个时候，计算出来的值还是初始的最大值，说明无解，设置为 -1
        if (memo[amount] == Integer.MAX_VALUE) {
            memo[amount] = -1;
        }
        return memo[amount];
    }

    @Test
    public void test() {
        Assert.assertEquals(0, coinChange(new int[] {1}, 0));
        Assert.assertEquals(-1, coinChange(new int[] {2}, 3));
        Assert.assertEquals(3, coinChange(new int[] {1, 2, 5}, 11));
    }

}
