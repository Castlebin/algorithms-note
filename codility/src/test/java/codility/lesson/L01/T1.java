package codility.lesson.L01;

import org.junit.Assert;
import org.junit.Test;

/**
 BinaryGap

 Find the longest sequence of zeros in binary representation of an integer.

 https://app.codility.com/programmers/lessons/1-iterations/binary_gap/
 */
public class T1 {

    public int solution(int N) {
        int maxGap = 0;
        int lastOneIndex = -1;
        int num = N;
        for (int i = 0; i < 32; i++) {
            int b = num & 1;
            if (b == 1) {
                if (lastOneIndex != -1) {
                    maxGap = Math.max(maxGap, i - lastOneIndex - 1);
                }
                lastOneIndex = i;
            }
            num >>= 1;
        }
        return maxGap;
    }

    @Test
    public void test() {
        Assert.assertEquals(1, solution(0B101));  // 5 = 0B101
        Assert.assertEquals(2, solution(9));      // 9 = 0b1001
        Assert.assertEquals(2, solution(0b1001)); // 9 = 0b1001
        Assert.assertEquals(1, solution(20));     // 20 = 0b10100
        Assert.assertEquals(0, solution(15));     // 15 = 0b1111
    }

}
