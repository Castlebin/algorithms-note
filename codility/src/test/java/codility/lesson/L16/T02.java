package codility.lesson.L16;

import org.junit.Assert;
import org.junit.Test;

/**
 TieRopes

 Tie adjacent ropes to achieve the maximum number of ropes of length >= K.
 */
public class T02 {

    public int solution(int K, int[] A) {
        int count = 0; // 用于计数满足条件的绳子数量
        int currentLength = 0; // 当前绳子的长度
        for (int index = 0; index < A.length; index++) {
            currentLength += A[index]; // 累加当前绳子的长度
            if (currentLength >= K) { // 如果当前绳子的长度大于等于K
                count++; // 满足条件的绳子数量加一
                currentLength = 0; // 重置当前绳子的长度
            }
        }
        return count;
    }

    @Test
    public void test() {
        Assert.assertEquals(3, solution(4, new int[] {1, 2, 3, 4, 1, 1, 3}));

        Assert.assertEquals(1, solution(10, new int[]{9, 1,1,1,1,1,1,1,1,1,1})); // 9 后面 10个 1
    }

}
