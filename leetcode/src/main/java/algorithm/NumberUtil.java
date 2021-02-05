package algorithm;

import org.junit.Assert;
import org.junit.Test;

public class NumberUtil {

    /**
     * 不用加减乘除做加法
     */
    public long add(int a, int b) {
        while (b != 0) {
            // 不带进位的加法，异或
            int tmp = a ^ b;
            // 进位
            b = (a & b) << 1;
            a = tmp;
        }
        return a;
    }

    @Test
    public void testAdd() {
        int a = 10, b = 20;
        Assert.assertEquals(30, add(a, b));
        a = -10; b = 20;
        Assert.assertEquals(10, add(a, b));
        a = 10; b = -20;
        Assert.assertEquals(-10, add(a, b));
    }

}
