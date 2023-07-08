package leetcode.N200_N299;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用 埃式质数筛法，求出 n 以内的所有质数 [1, n)，注意，不包含 n
 */
public class T204_0 {

    public List<Integer> primes(int n) {
        List<Integer> primes = new ArrayList<>();
        boolean[] isPrime = new boolean[n];
        // 先假设所有的数，都是质数
        for (int num = 2; num < n; num++) {
            isPrime[num] = true;
        }
        // 从 2 开始筛
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (isPrime[i]) {
                // 筛除所有它的倍数 （注意，从 i * i 开始即可，因为更小的数，必然在前面的迭代过程中，已经被筛过了）
                for (int j = i * i; j < n; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        for (int num = 2; num < n; num++) {
            if (isPrime[num]) {
                primes.add(num);
            }
        }
        return primes;
    }

}
