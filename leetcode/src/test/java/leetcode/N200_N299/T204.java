package leetcode.N200_N299;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 204. 计数质数

 给定整数 n ，返回 所有小于非负整数 n 的质数的数量 。

 https://leetcode.cn/problems/count-primes/
 */
public class T204 {

    /**
     * 埃式质数筛法。 时间复杂度 O(nloglogn)，空间复杂度 O(n)
     *
     * 从 2 开始，筛除 2 的倍数 （从 2 * 2 开始筛，前面的不用管）
     * 从 3 开始，筛除 3 的倍数 （从 3 * 3 开始筛，前面的不用管）
     * 从 5 开始，筛除 5 的倍数 （从 5 * 5 开始筛，前面的不用管）（很简单，因为假设要筛 5 * 3，那么，在 3 的时候，就已经被筛过了）
     * 依次类推
     */
    class Solution_1 {
        public int countPrimes(int n) {
            boolean[] isPrime = new boolean[n];
            // 先假设从 2 开始的所有数都是质数
            for (int i = 2; i < n; i++) {
                isPrime[i] = true;
            }
            // 埃式质数筛法
            for (int i = 2; i <= (int) Math.sqrt(n); i++) {
                if (isPrime[i]) {// 是质数
                    // 要尝试的数，从 i * i 开始，因为假设如果是 i * (i - 1) 时，在 i - 1 这个迭代，就已经被计算过了
                    for (int num = i * i; num < n; num += i) {
                        isPrime[num] = false;
                    }
                }
            }
            int numPrime = 0;
            for (int i = 2; i < n; i++) {
                if (isPrime[i]) {
                    numPrime++;
                }
            }
            return numPrime;
        }
    }

    /**
     * 线性筛 。时间复杂度 O(n)，空间复杂度 O(n)
     * 核心原因在于 埃氏筛其实还是存在冗余的标记操作，比如对于 45 这个数，它会同时被 3, 5 两个数标记为合数，
     * 因此我们优化的目标是让每个合数只被标记一次，这样时间复杂度即能保证为 O(n)
     *
     * 为了实现该算法，需要有一个额外的集合，用于记录当前发现的质数
     *
     * 链接：https://leetcode.cn/problems/count-primes/solution/ji-shu-zhi-shu-by-leetcode-solution/
     */
    class Solution_2 {
        public int countPrimes(int n) {
            // 神奇啊，把这行代码放到最前面，leetcode 解题能快 300 毫秒！！
            List<Integer> primes = new ArrayList<>();
            boolean[] isPrime = new boolean[n];
            for (int i = 2; i < n; i++) {
                isPrime[i] = true;
            }
            // List<Integer> primes = new ArrayList<>(); // 这行代码移到最前面
            for (int i = 2; i < n; ++i) {
                if (isPrime[i]) {
                    primes.add(i);
                }
                for (int j = 0; j < primes.size() && i * primes.get(j) < n; ++j) {
                    isPrime[i * primes.get(j)] = false;
                    if (i % primes.get(j) == 0) {
                        break;
                    }
                }
            }
            return primes.size();
        }
    }

    @Test
    public void test() {
        Assert.assertEquals(0, new Solution_1().countPrimes(0));
        Assert.assertEquals(0, new Solution_1().countPrimes(1));
        Assert.assertEquals(4, new Solution_1().countPrimes(10));
        Assert.assertEquals(25, new Solution_1().countPrimes(100));
        Assert.assertEquals(168, new Solution_1().countPrimes(1000));
    }

}
