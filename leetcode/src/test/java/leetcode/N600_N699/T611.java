package leetcode.N600_N699;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import common.NumUtil;

/**
 * 611. 有效三角形的个数
 * 给定一个包含非负整数的数组 nums ，返回其中可以组成三角形三条边的三元组个数。
 */
public class T611 {

    public int triangleNumber(int[] nums) {
        if (nums == null || nums.length < 3) {
            return 0;
        }
        Arrays.sort(nums);
        int count = 0;
        int n = nums.length;
        for (int i = 0; i <= n - 3; i++) {
            if (nums[i] <= 0) {
                continue;
            }
            // 双指针，j 、k 分别指向 i 之后的两个元素。因为，只有 j 移动，k 才会需要移动，这里只需要 j 这一层循环
            for (int j = i + 1, k = j + 1; j <= n - 2; j++) {
                while (k <= n - 1 && nums[i] + nums[j] > nums[k]) {
                    k++;
                }
                count += k - j - 1;
            }
        }
        return count;
    }

    @Test
    public void test() {
        Assert.assertEquals(3, triangleNumber(new int[] {2, 2, 3, 4}));
        Assert.assertEquals(0, triangleNumber(new int[] {0, 0, 0}));
        Assert.assertEquals(4, triangleNumber(new int[] {4, 2, 3, 4}));
        Assert.assertEquals(161700, triangleNumber(NumUtil.generateRandomArray(100, 2, 2)));
    }

}
