package sword;

import org.junit.Assert;
import org.junit.Test;

/**
 剑指 Offer 46. 把数字翻译成字符串
 给定一个数字，我们按照如下规则把它翻译为字符串：
   0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。
   一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。

 示例 1:
 输入: 12258
 输出: 5
 解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"

 提示：
    0 <= num < 2**31
 */
public class S046 {

    /**
     * 递推公式：定义 f(i) 为从第 i 个字符 开始，不同翻译的个数
     * 那么有 f(i) = f(i + 1)  或 f(i) = f(i + 1) + f(i + 2)
     *
     * 取决于 [i, i+1] 两个数字，是否能翻译成一个 字母
     */
    public int translateNum(int num) {
        if (num < 0) {
            return 0;
        }
        String str = String.valueOf(num);
        // 为了方便计算，并减少条件判断，假设起始位置为 [0, N] 一共 N + 1 个，最后一个就是字符串的末尾
        int[] cache = new int[str.length() + 1];
        cache[str.length()] = 1;
        cache[str.length() - 1] = 1;
        for (int index = str.length() - 2; index >= 0; index--) {
            cache[index] = cache[index + 1];
            int parsed = Integer.parseInt(str.substring(index, index + 2));
            // leetcode 上判断 子串 01 只能按 1 处理，而不能翻译为 0、1， 所以得稍微改改
            // 虽然我认为我这样更合理
         // if (parsed >= 0 && parsed <= 25) {
            if (parsed >= 10 && parsed <= 25) {
                cache[index] += cache[index + 2];
            }
        }
        return cache[0];
    }

    @Test
    public void test() {
        Assert.assertEquals(2, translateNum(25));
        Assert.assertEquals(5, translateNum(12258));
    }

}
