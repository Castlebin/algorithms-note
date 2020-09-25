package sword;

import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 剑指 Offer 45. 把数组排成最小的数
 输入一个 **非负整数** 数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。

 示例 1:
 输入: [10,2]
 输出: "102"

 示例 2:
 输入: [3,30,34,5,9]
 输出: "3033459"

 提示:

 0 < nums.length <= 100
 说明:

 输出结果可能非常大，所以你需要返回一个字符串而不是整数
 拼接起来的数字可能会有前导 0，最后结果不需要去掉前导 0
 */
public class S045 {

    /**
     * 将数字数组元素按照字符串方式进行排序，排序的 方法为：
     * 若 o1 + o2 < o2 + o1  （结果都是字符串），则 o1 排在 o2 前面，然后顺序拼成字符串即可
     */
    public String minNumber(int[] nums) {
        return Arrays.stream(nums)
                .boxed()
                .map(String::valueOf)
                .sorted((o1, o2) -> (o1 + o2).compareTo(o2 + o1))
                .collect(Collectors.joining());
    }

    @Test
    public void test() {

    }

}
