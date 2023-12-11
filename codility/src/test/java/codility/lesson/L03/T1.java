package codility.lesson.L03;

import org.junit.Assert;
import org.junit.Test;

/**
 FrogJmp

 Count minimal number of jumps from position X to Y.

 https://app.codility.com/programmers/lessons/3-time_complexity/frog_jmp/
 */
public class T1 {

    public int solution(int X, int Y, int D) {
        int distances = Y - X;
        int steps = distances / D;
        if (distances % D > 0) {
            steps++;
        }
        return steps;
    }

    @Test
    public void test() {
        Assert.assertEquals(3, solution(10, 85, 30));
    }

}
