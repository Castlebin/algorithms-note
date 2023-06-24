package codility.lesson.L09;

import org.junit.Assert;
import org.junit.Test;

/**
 MaxSliceSum

 Find a maximum sum of a compact subsequence of array elements.

 https://app.codility.com/programmers/lessons/9-maximum_slice_problem/max_double_slice_sum/

 注意：按题目要求，至少得包含一个元素（不能一个也不选）
 */
public class T2 {

    public int solution(int[] A) {
        int maxSliceSum = 0, curSliceSum = 0, max = A[0];
        for (int i = 0; i < A.length; i++) {
            curSliceSum += A[i];
            if (curSliceSum > maxSliceSum) {
                maxSliceSum = curSliceSum;
            } else if (curSliceSum < 0) {
                curSliceSum = 0;
            }
            // 兼容全部为负数的情况，这种情况，选最大的负数
            max = Math.max(max, A[i]);
        }
        return max > 0 ? maxSliceSum : max;
    }

    @Test
    public void test() {
        Assert.assertEquals(5, solution(new int[] {3, 2, -6, 4, 0}));
        Assert.assertEquals(-10, solution(new int[] {-10}));
        Assert.assertEquals(-2, solution(new int[] {-10, -8, -9, -2, -7}));
    }

}
