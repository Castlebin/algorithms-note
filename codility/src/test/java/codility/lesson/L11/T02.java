package codility.lesson.L11;

import org.junit.Assert;
import org.junit.Test;

/**
 CountSemiprimes

 Count the semiprime numbers in the given range [a..b]

 https://app.codility.com/programmers/lessons/11-sieve_of_eratosthenes/count_semiprimes/
 */
public class T02 {

    class Solution_1 {
        public int[] solution(int N, int[] P, int[] Q) {
            int[] result = new int[P.length];
            // 1. 利用埃式素数筛法，标记出素数
            boolean[] isPrime = new boolean[N + 1];
            for (int i = 2; i < N + 1; i++) {
                isPrime[i] = true;
            }
            for (int i = 2; i <= Math.sqrt(N + 1); i++) {
                if (isPrime[i]) {
                    for (int j = i * i; j <= N; j += i) {
                        isPrime[j] = false;
                    }
                }
            }
            // 2. 生成半素数表 （时间复杂度 O(N)）  （这个是快的关键，生成，而不是判断）
            boolean[] isSemiPrime = new boolean[N + 1];
            for (int i = 2; i <= N; i++) {
                if (isPrime[i]) {
                    for (int j = i; j <= N / i + 1; j++) {
                        if (isPrime[j] && (i * j <= N)) {
                            isSemiPrime[i * j] = true;
                        }
                    }
                }
            }
            // 生成前缀和 （快的第二个关键，利用前缀和，直接能求解）
            int[] prefixSum = new int[N + 1];
            for (int num = 4; num <= N; num++) {
                if (isSemiPrime[num]) {
                    prefixSum[num] = prefixSum[num - 1] + 1;
                } else {
                    prefixSum[num] = prefixSum[num - 1];
                }
            }
            // 利用前缀和，就可以直接求解了
            for (int i = 0; i < P.length; i++) {
                result[i] = prefixSum[Q[i]] - prefixSum[P[i] - 1];
            }

            return result;
        }
    }

    @Test
    public void test() {
        Assert.assertArrayEquals(new int[] {10, 4, 0},
                new Solution_1().solution(26, new int[] {1, 4, 16}, new int[] {26, 10, 20}));
    }

}
