package sword;

import org.junit.Assert;
import org.junit.Test;

/**
 11. 旋转数组的最小数字 （理解题意，描述能力太差了）

 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。例如，数组[3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。

 示例 1：
 输入：[3,4,5,1,2]
 输出：1

 示例 2：
 输入：[2,2,2,0,1]
 输出：0

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof

 三个数字都相同时，没办法，只能退化为顺序查找
 */
public class S011 {

    public int minArray(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            throw new RuntimeException("输入不正确");
        }
        int begin = 0, end = numbers.length - 1;
        // 必须是 小于
        if (numbers[begin] < numbers[numbers.length - 1]) {
            return numbers[begin];
        }

        // 注意，两个区间，一定不能交叉，所以这里是 begin < end - 1
        while (begin < end - 1) {
            int mid = (end - begin) / 2 + begin;
            // 三个数字都相同时，只能退化为顺序查找
            if (numbers[begin] == numbers[mid]
                && numbers[mid] == numbers[end]) {
                return findMinInOrder(numbers, begin, end);
            }
            if (numbers[mid] >= numbers[begin]) {
                begin = mid;
            } else if (numbers[mid] <= numbers[end]) {
                end = mid;
            }
        }

        // return numbers[end] 注意画图理解
        return numbers[end];
    }

    // 顺序查找，找到区间内的最小值
    private int findMinInOrder(int[] numbers, int begin, int end) {
        int min = numbers[begin];
        // 如果是同一个位置，则表明找到了，此时，查找的区间也只有1，循环不会执行
        for (int i = begin; i <= end; i++) {
            if (numbers[i] < min) {
                min = numbers[i];
            }
        }
        return min;
    }

    @Test
    public void test() {
        Assert.assertEquals(1, minArray(new int[]{3,1,3}));
    }

}
