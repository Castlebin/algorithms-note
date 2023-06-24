package codility.lesson.L08;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

/**
 EquiLeader

 Find the index S such that the leaders of the sequences A[0], A[1], ..., A[S] and A[S + 1], A[S + 2], ..., A[N - 1] are the same.

 https://app.codility.com/programmers/lessons/8-leader/equi_leader/
 */
public class T2 {

    public int solution(int[] A) {
        // 先求出 Leader 值，并且记住它的个数。（如果没有 Leader，返回 0）
        Map<Integer, Integer> countMap = new HashMap<>();
        int leaderCount = 0, leader = Integer.MIN_VALUE;
        for (int i = 0; i < A.length; i++) {
            Integer t = countMap.get(A[i]);
            if (t == null) {
                countMap.put(A[i], 1);
            } else {
                countMap.put(A[i], t + 1);
                if (countMap.get(A[i]) > A.length / 2) {
                    leader = A[i];
                    leaderCount = countMap.get(A[i]);
                }
            }
        }
        // 没有 leader
        if (leaderCount == 0) {
            return 0;
        }

        int count = 0;
        int curLeaders = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] == leader) {
                curLeaders++;
            }
            if (curLeaders > (i + 1) / 2 // 前半部分满足条件
                    && (leaderCount - curLeaders > (A.length - i - 1) / 2)) { // 后半部分也满足条件
                count++;
            }
        }
        return count;
    }

    @Test
    public void test() {
        Assert.assertEquals(2, solution(new int[] {4, 3, 4, 4, 4, 2}));
    }

}
