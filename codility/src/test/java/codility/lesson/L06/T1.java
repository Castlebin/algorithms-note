package codility.lesson.L06;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

/**
 Distinct

 Compute number of distinct values in an array.

 https://app.codility.com/programmers/lessons/6-sorting/distinct/
 */
public class T1 {

    /**
     * 平均时间复杂度 O(N * log(N))
     * 时间复杂度 O(N * N)，空间复杂度 O(1)
     */
    public int solution(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        Arrays.sort(A);
        int distinct = 1;
        int last = A[0];
        for (int i = 1; i < A.length; i++) {
            if (A[i] != last) {
                distinct++;
                last = A[i];
            }
        }
        return distinct;
    }

    /**
     * 时间复杂度 O(N)，空间复杂度 O(N)
     */
    public int solution_0(int[] A) {
        Set<Integer> set = new HashSet<>();
        for (int a : A) {
            set.add(a);
        }
        return set.size();
    }

    @Test
    public void test() {
        Assert.assertEquals(3, solution(new int[] {2, 1, 1, 2, 3, 1}));
    }

}
