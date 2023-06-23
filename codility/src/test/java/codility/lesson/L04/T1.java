package codility.lesson.L04;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

/**
 FrogRiverOne

 Find the earliest time when a frog can jump to the other side of a river.

 https://app.codility.com/programmers/lessons/4-counting_elements/frog_river_one/
 */
public class T1 {

    public int solution(int X, int[] A) {
        Set<Integer> set = new HashSet<>();
        for (int i = 1; i <= X; i++) {
            set.add(i);
        }
        for (int i = 0; i < A.length; i++) {
            set.remove(A[i]);
            if (set.size() == 0) {
                return i;
            }
        }
        return -1;
    }

    @Test
    public void test() {
        Assert.assertEquals(6, solution(5, new int[] {1, 3, 1, 4, 2, 3, 5, 4}));
    }

}
