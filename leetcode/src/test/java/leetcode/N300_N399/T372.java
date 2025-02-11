package leetcode.N300_N399;

import org.junit.Assert;
import org.junit.Test;

/**
 * 372. 超级次方
 * 计算 a 的 b 次方对 1337 取模，其中 b 是一个非负数组表示的整数
 * 例如：b = [1,2] 表示 12，即需要计算 a^12 % 1337
 */
public class T372 {
    // 取模的基数
    private static final int MOD = 1337;

    /**
     * 计算超级次方
     * @param a 底数
     * @param b 用数组表示的指数
     * @return a^b % 1337
     */
    public int superPow(int a, int[] b) {
        return superPowHelper(a, b, b.length - 1);
    }

    /**
     * 递归计算超级次方
     * 原理：a^123 = a^3 * (a^12)^10
     * @param a 底数
     * @param b 指数数组
     * @param index 当前处理的数位索引
     * @return 超级次方结果
     */
    private int superPowHelper(int a, int[] b, int index) {
        // 基本情况：处理最后一位
        if (index == 0) {
            return powMod(a, b[index]);
        }
        // 递归计算：当前位的贡献 * (前面位数的结果的10次方)
        return (powMod(a, b[index]) * 
               powMod(superPowHelper(a, b, index - 1), 10)) % MOD;
    }

    /**
     * 计算 (a^k) % MOD，使用快速幂算法
     * @param a 底数
     * @param k 指数
     * @return 幂运算结果
     */
    private int powMod(int a, int k) {
        a %= MOD;  // 预处理，确保 a 在 MOD 范围内
        // 特殊情况处理
        if (k == 0 || a == 1) return 1;
        if (k == 1) return a;

        // 快速幂算法
        int halfPow = powMod(a, k / 2);
        int result = (halfPow * halfPow) % MOD;
        
        // 如果是奇数次幂，还需要多乘一次底数
        if (k % 2 == 1) {
            result = (result * a) % MOD;
        }
        return result;
    }

    @Test
    public void test() {
        Assert.assertEquals(8, superPow(2, new int[]{3}));          // 2^3 = 8
        Assert.assertEquals(1024, superPow(2, new int[]{1, 0}));    // 2^10 = 1024
        Assert.assertEquals(1, superPow(1, new int[]{4, 3, 3, 8, 5, 2}));  // 1^任何数 = 1
        Assert.assertEquals(1198, superPow(2147483647, new int[]{2, 0, 0}));  // 大数测试
    }
}
