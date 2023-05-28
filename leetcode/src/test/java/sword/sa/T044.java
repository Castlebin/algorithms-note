package sword.sa;

import org.junit.Assert;
import org.junit.Test;

/**
 剑指 Offer 44. 数字序列中某一位的数字
 数字以0123456789101112131415…的格式序列化到一个字符序列中。在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，等等。

 请写一个函数，求任意第n位对应的数字。

 示例 1：
 输入：n = 3
 输出：3

 示例 2：
 输入：n = 11
 输出：0

 限制：
 0 <= n < 2^31
 */
public class T044 {

    public int findNthDigit(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        if (n < 10) {
            return n;
        }
        long numLength = 2;
        long beginNum = 10;
        long nums = 90;
        long endNum = 99;
        long beginLength = 10;
        long endLength = beginLength + nums * numLength - 1;
        while (endLength < n) {
            numLength++;
            beginNum = endNum + 1;
            nums *= 10;
            endNum = beginNum + nums - 1;
            beginLength = endLength + 1;
            endLength = beginLength + nums * numLength - 1;
        }
        // 好了，到此，算出来 numLength 和 begin 了
        // 再算一下，它是 这个区间的第几个数字
        long lenIndex = (n - beginLength) / numLength;
        long mod = (n - beginLength) % numLength;
        // 好了，现在可以知道 它是属于哪个数字的哪一位了

        long num = beginNum + lenIndex;
        String resultStr = String.valueOf(num).substring((int) mod, (int) mod + 1);
        return Integer.parseInt(resultStr);
    }

    @Test
    public void test() {
        Assert.assertEquals(3, findNthDigit(3));
        Assert.assertEquals(0, findNthDigit(11));
    }

}
