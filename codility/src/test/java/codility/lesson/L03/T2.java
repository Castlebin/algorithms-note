package codility.lesson.L03;

import org.junit.Assert;
import org.junit.Test;

/**
 PermMissingElem

 Find the missing element in a given permutation.

 https://app.codility.com/programmers/lessons/3-time_complexity/perm_missing_elem/
 */
public class T2 {

    /**
     * 直接数学方法求解
     * 时间复杂度 O(N)，空间复杂度 O(1)
     */
    public int solution(int[] A) {
        int n = A.length + 1;
        long result = (long) n * (n + 1) / 2;
        for (int num : A) {
            result -= num;
        }
        return (int) result;
    }

    /**
     * 位运算方法求解
     * 时间复杂度 O(N)，空间复杂度 O(1)
     *
     * 原理很简单，因为 num ^ num = 0，所以将所有数字和 1 ~ N 的数字异或，最后的结果就是缺失的数字
     */
    public int solution_1(int[] A) {
        int n = A.length + 1;
        int result = n;
        for (int i = 0; i < A.length; i++) {
            result ^= A[i];
            result ^= i + 1;
        }
        return result;
    }

    /**
     * 申请大小为 N + 1 的额外空间的数组，标记数字是否出现
     * 时间复杂度 O(N)，空间复杂度 O(N)
     */
    public int solution_2(int[] A) {
        boolean[] flag = new boolean[A.length + 1];
        for (int num : A) {
            flag[num - 1] = true;
        }
        for (int i = 0; i < flag.length; i++) {
            if (!flag[i]) {
                return i + 1;
            }
        }
        return A.length + 1;
    }

    @Test
    public void test() {
        Assert.assertEquals(4, solution(new int[] {2, 3, 1, 5}));
    }

}
