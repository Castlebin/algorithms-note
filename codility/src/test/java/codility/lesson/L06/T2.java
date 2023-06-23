package codility.lesson.L06;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

/**
 MaxProductOfThree

 Maximize A[P] * A[Q] * A[R] for any triplet (P, Q, R).

 https://app.codility.com/programmers/lessons/6-sorting/max_product_of_three/
 */
public class T2 {

    /**
     * 平均时间复杂度：O(N * log(N))
     */
    public int solution(int[] A) {
        // 先排序
        Arrays.sort(A);
        // 对于排序数组，3 个数乘积最大，只有两种可能性：
        // 1. 最后 3 个数；
        // 2. 前两个最小的负数，最后一个最大的整数
        int max = A[A.length - 3] * A[A.length - 2] * A[A.length - 1];
        max = Math.max(max, A[0] * A[1] * A[A.length - 1]);
        return max;
    }

    @Test
    public void test() {
        Assert.assertEquals(60, solution(new int[] {-3, 1, 2, -2, 5, 6}));
    }

}
