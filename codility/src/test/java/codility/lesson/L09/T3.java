package codility.lesson.L09;

import org.junit.Assert;
import org.junit.Test;

/**
 MaxDoubleSliceSum

 Find the maximal sum of any double slice.

 https://app.codility.com/programmers/lessons/9-maximum_slice_problem/max_double_slice_sum/
 */
public class T3 {

    public int solution(int[] A) {
        // 使用两个数组，分别保存从前面开始能贡献的最大值和从后面开始能够贡献的最大值
        int[] prefix = new int[A.length];
        int[] suffix = new int[A.length];
        for (int i = 1; i < A.length - 1; i++) {
            prefix[i] = Math.max(prefix[i - 1] + A[i], 0);
        }
        for (int i = A.length - 2; i > 0; i--) {
            suffix[i] = Math.max(suffix[i + 1] + A[i], 0);
        }
        int maxDoubleSliceSum = 0;
        for (int i = 1; i < A.length - 1; i++) {
            maxDoubleSliceSum = Math.max(maxDoubleSliceSum, prefix[i - 1] + suffix[i + 1]);
        }
        return maxDoubleSliceSum;
    }

    @Test
    public void test() {
        Assert.assertEquals(17, solution(new int[] {5, 17, 0, 3}));
        Assert.assertEquals(17, solution(new int[] {3, 2, 6, -1, 4, 5, -1, 2}));
    }

}
