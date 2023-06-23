package codility.lesson.L05;

import org.junit.Assert;
import org.junit.Test;

/**
 PassingCars
 Count the number of passing cars on the road.

 https://app.codility.com/programmers/lessons/5-prefix_sums/passing_cars/

 前缀和 思想。时间复杂度 O(N)
 */
public class T1 {

    private static final int MAX = 1_000_000_000;

    public int solution(int[] A) {
        int result = 0;
        int countZero = 0; // 从 [0, i] ，其中 0 的个数
        for (int i = 0; i < A.length; i++) {
            if (A[i] == 0) {
                countZero++;
            } else {// 碰到 1 的话，就看看它会和几个 0 相向而行
                result += countZero;

                // 题目要求结果大于 1,000,000,000 时，返回 -1
                if (result > MAX) {
                    return -1;
                }
            }
        }
        return result;
    }

    @Test
    public void test() {
        Assert.assertEquals(5, solution(new int[] {0, 1, 0, 1, 1}));
    }

}
