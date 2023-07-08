package codility.lesson.L11;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

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
     * 用两个 Map 来减少重复计算，有那么一点点优化，变化不大。哎
     *
     * Correctness 100 % ，Performance 25 % 。综合：Task Score：66 %
     */
    class Solution_1 {// Detected time complexity: O(N ** 2)
        public int[] solution(int[] A) {
            int n = A.length;
            int[] result = new int[n];
            // 首先，用一个 map，统计每个数字出现的次数
            Map<Integer, Integer> numCountMap = new HashMap<>();
            for (int i = 0; i < n; i++) {
                int num = A[i];
                if (!numCountMap.containsKey(num)) {
                    numCountMap.put(num, 1);
                } else {
                    numCountMap.put(num, numCountMap.get(num) + 1);
                }
            }
            // 再用一个 map，记录每个数字的计算结果，避免相同数字的重复计算
            Map<Integer, Integer> numResultCountMap = new HashMap<>();
            for (int i = 0; i < n; i++) {
                int num = A[i];
                if (numResultCountMap.containsKey(num)) {
                    // 这个数字是重复数字，已经计算过了，直接拿答案
                    result[i] = numResultCountMap.get(num);
                    continue;
                }
                // 计算一个数字的答案（没有被计算过）
                for (Map.Entry<Integer, Integer> entry : numCountMap.entrySet()) {
                    if (num % entry.getKey() != 0) {
                        result[i] += entry.getValue();
                    }
                }
            }
            return result;
        }
    }

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

    @Test
    public void test() {
        Assert.assertArrayEquals(new int[] {2, 4, 3, 2, 0}, new Solution_1().solution(new int[] {3, 1, 2, 3, 6}));
    }

}
