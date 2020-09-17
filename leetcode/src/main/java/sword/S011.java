package sword;

import org.junit.Assert;
import org.junit.Test;

/**
 11. 旋转数组的最小数字 （理解题意）

 三个数字都相同时，没办法，只能退化为顺序查找
 */
public class S011 {

    public int minArray(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            throw new RuntimeException("输入不正确");
        }
        int begin = 0, end = numbers.length - 1;
        if (numbers[begin] < numbers[numbers.length - 1]) {
            return numbers[begin];
        }
        int mid;
        while (begin < end - 1) {
            mid = (begin + end) / 2;
            // 三个数字都相同时，没办法，只能退化为顺序查找
            if (numbers[begin] == numbers[mid]
                && numbers[mid] == numbers[end]) {
                return findMinInOrder(numbers, begin, end);
            } else if (numbers[mid] >= numbers[begin]) {
                begin = mid;
            } else if (numbers[mid] <= numbers[end]) {
                end = mid;
            }
        }

        return numbers[end];
    }

    private int findMinInOrder(int[] numbers, int begin, int end) {
        int min = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
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
