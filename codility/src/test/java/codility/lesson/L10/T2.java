package codility.lesson.L10;

import org.junit.Assert;
import org.junit.Test;

/**
 MinPerimeterRectangle

 Find the minimal perimeter of any rectangle whose area equals N.

 https://app.codility.com/programmers/lessons/10-prime_and_composite_numbers/min_perimeter_rectangle/
 */
public class T2 {

    public int solution(int N) {
        int min = 2 * (N + 1);
        for (int i = 2; i <= Math.sqrt(N); i++) {
            if (N % i == 0) {
                min = Math.min(min, 2 * (i + N / i));
            }
        }
        return min;
    }

    @Test
    public void test() {
        Assert.assertEquals(22, solution(30));
        Assert.assertEquals(24, solution(36));
    }

}
