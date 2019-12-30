package algorithm;

import org.junit.Assert;
import org.junit.Test;

/**
 * 62. Unique Paths
 */
public class N0062 {

    public int uniquePaths(int length, int width) {
        int n = length + width - 2;
        int m = length > width ? width - 1 : length - 1;

        // 求 C n 取 m
        long result = 1;// 可能会溢出，所以此处必须为long
        for (int i = n; i > n - m; i--) {
            result *= i;
        }
        for (int i = m; i > 1; i--) {
            result /= i;
        }

        return (int)result;
    }

    @Test
    public void test() {
        Assert.assertEquals(2, uniquePaths(2, 2));
        Assert.assertEquals(3, uniquePaths(3, 2));
        Assert.assertEquals(28, uniquePaths(7, 3));
    }

}
