package codility.lesson.L04;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

/**
 PermCheck

 Check whether array A is a permutation.

 https://app.codility.com/programmers/lessons/4-counting_elements/perm_check/
 */
public class T2 {

    public int solution(int[] A) {
        Set<Integer> set = new HashSet<>();
        for (int i = 1; i <= A.length; i++) {
            set.add(i);
        }
        for (int num : A) {
            if (num < 1 || num > A.length) {
                return 0;
            }
            boolean removed = set.remove(num);
            if (!removed) {
                return 0;
            }
        }
        return set.isEmpty() ? 1 : 0;
    }

    /**
     * 用 与或 运算  (如果是排列，那么数组和 1 ~ N 的数字异或的结果应该是 0)
     */
    public int solution_1(int[] A) {
        int result = 0;
        for (int i = 0; i < A.length; i++) {
            result ^= (i + 1) ^ A[i];
        }
        return result == 0 ? 1 : 0;
    }

    @Test
    public void test() {
        Assert.assertEquals(1, solution(new int[] {4, 1, 3, 2}));
        Assert.assertEquals(0, solution(new int[] {4, 1, 3}));
    }

}
