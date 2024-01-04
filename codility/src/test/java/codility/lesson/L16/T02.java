package codility.lesson.L16;

import org.junit.Assert;
import org.junit.Test;

/**
 TieRopes

 Tie adjacent ropes to achieve the maximum number of ropes of length >= K.

 （本题表达不清，实际上需要说明，要求绑定后的长度 >= K，但是每次这个长度都应该尽量小（到达这个长度就停止累加））
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
    }

}
