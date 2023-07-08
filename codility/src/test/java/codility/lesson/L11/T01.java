package codility.lesson.L11;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

/**
 CountNonDivisible

 Calculate the number of elements of an array that are not divisors of each element.

 https://app.codility.com/programmers/lessons/11-sieve_of_eratosthenes/count_non_divisible/

 计算数组中不是每个元素的因子的元素数量。
 */
public class T01 {

    /**
     * 完全的暴力解法，时间复杂度 O(N ^ 2)。
     *
     * Correctness 100 % ，Performance 0 % 。综合：Task Score：55 %
     */
    class Solution_0 {// Detected time complexity: O(N ** 2)

        public int[] solution(int[] A) {
            int n = A.length;
            int[] result = new int[n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (j != i && (A[i] % A[j] != 0)) {
                        result[i]++;
                    }
                }
            }
            return result;
        }
    }

    /**
     * 利用 Map 计数，以及因式分解的数学原理，减少计算次数。计数的是除数的个数，用 N 减去即可得到 非除数的个数
     *
     * Correctness 100 % ，Performance 100 % 。综合：Task Score：100 %
     */
    class Solution_2 {// Detected time complexity: O(N * log(N)) or O(N ** 2) 。应该是 O( N * sqrt(N) )

        public int[] solution(int[] A) {
            int N = A.length;

            // 计算每个数字出现的次数
            Map<Integer, Integer> countMap = new HashMap<>();
            for (int num : A) {
                countMap.put(num, countMap.getOrDefault(num, 0) + 1);
            }

            // 对每个数字，计算它的除数的个数
            Map<Integer, Integer> divisorCountMap = new HashMap<>();
            for (int num : countMap.keySet()) {// 减少计算次数.    O(N)
                int divisorCount = 0;
                for (int i = 1; i * i <= num; i++) {// 减少计算次数。因为如果对一个数进行因式分解，比如有一个因数，会是小于等于 sqrt(n)
                    if (num % i == 0) {
                        divisorCount += countMap.getOrDefault(i, 0); // 直接用 getOrDefault() ，避免了判断
                        if (i * i != num) {// 减少计算次数，最多一次可以判断两个数。两个因子相同的话，不能重复计算
                            divisorCount += countMap.getOrDefault(num / i, 0);
                        }
                    }
                }
                divisorCountMap.put(num, divisorCount);
            }

            // 现在很简单了。对于每个数，N 减去它的除数的个数，就是 不是除数 的个数
            int[] result = new int[N];
            for (int i = 0; i < N; i++) {
                result[i] = N - divisorCountMap.get(A[i]);
            }

            return result;
        }
    }

    @Test
    public void test() {
        Assert.assertArrayEquals(new int[] {0}, new Solution_2().solution(new int[] {2}));
        Assert.assertArrayEquals(new int[] {2, 4, 3, 2, 0}, new Solution_2().solution(new int[] {3, 1, 2, 3, 6}));
    }

}
