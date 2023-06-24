package codility.lesson.L08;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;

/**
 Dominator

 Find an index of an array such that its value occurs at more than half of indices in the array.

 https://app.codility.com/programmers/lessons/8-leader/dominator/
 */
public class T1 {
    /**
     * 使用数学原理，不借助额外空间，时间复杂度 O(N) 的算法。非常巧妙!!
     */

    /**
     * 借助排序，不借助额外空间的算法，时间复杂度 O(N * log(N))
     */

    /**
     * 时间复杂度 O(N)，空间复杂度 O(N)。解法和思路都简单，但借助了大小为 N 的空间
     */
    public int solution(int[] A) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            Integer count = countMap.get(A[i]);
            if (count == null) {
                countMap.put(A[i], 1);
            } else {
                countMap.put(A[i], count + 1);
            }
            if (countMap.get(A[i]) > A.length / 2) {
                return i; // 返回 index ，而不是值
            }
        }
        return -1;
    }

    @Test
    public void test() {
        Assert.assertTrue(Arrays.asList(0, 2, 4, 6, 7).contains(solution(new int[] {3, 4, 3, 2, 3, -1, 3, 3})));
    }

}
