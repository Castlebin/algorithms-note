package codility.lesson.L15;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

/**
 MinAbsSumOfTwo

 Find the minimal absolute value of a sum of two elements.
 */
public class T04 {

    /**
     引入了排序，所以算法复杂度为 O(N * log(N))
     */
    public int solution(int[] A) {
        int N = A.length;
        // 排序
        Arrays.sort(A);
        // 全是正数或者全是负数的情况，可以一步到位
        if (A[0] >= 0) {
            return 2 * A[0];
        }
        if (A[N - 1] <= 0) {
            return -2 * A[N - 1];
        }
        // 有正有负的数组
        int min = -2 * A[0];
        int left = 0, right = N - 1;
        while (left <= right) {
            int sum = A[left] + A[right];
            min = Math.min(Math.abs(sum), min);
            if (min == 0) {// 一个小小的优化
                break;
            }
            if (sum > 0) {
                right--;
            } else if (sum < 0) {
                left++;
            }
        }
        return min;
    }

    @Test
    public void test() {
        Assert.assertEquals(3, solution(new int[] {-8, 4, 5, -10, 3}));
    }

}
