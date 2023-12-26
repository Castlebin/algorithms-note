package codility.lesson.L15;

import java.util.*;

import org.junit.Assert;
import org.junit.Test;

/**
 AbsDistinct

 Compute number of distinct absolute values of sorted array elements.
 */
public class T01 {

    public int solution(int[] A) {
        return -1;
    }

    /**
     非常简单直观的算法，借助了额外空间，时间复杂度 O(N*log(N)) 或者可视为 O(N)。空间复杂度 O(N)
     本方法和课程所讲的毛毛虫法无关
     */
    public int solution_1(int[] A) {
        Set<Integer> absSet = new HashSet<>();
        for (int index = 0; index < A.length; index++) {
            absSet.add(Math.abs(A[index]));
        }
        return absSet.size();
    }

    @Test
    public void test() {
        Assert.assertEquals(5, solution(new int[] {-5, -3, -1, 0, 3, 6}));
    }

}
