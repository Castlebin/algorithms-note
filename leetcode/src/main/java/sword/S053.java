package sword;

import org.junit.Assert;
import org.junit.Test;

/**
 剑指 Offer 53 - I. 在排序数组中查找数字 I

 统计一个数字在排序数组中出现的次数。
*/
public class S053 {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // 题目只说了数组是排序数组，并未说明是递增还是递减，所以都转化为递增数组来处理
        if (nums[0] > nums[nums.length - 1]) {
            reverse(nums);
        }

        int begin = 0, end = nums.length - 1;
        while (begin <= end) {
            int middle = begin + (end - begin) / 2;
            if (nums[middle] > target) {
                end = middle - 1;
            } else if (nums[middle] < target) {
                begin = middle + 1;
            } else {
                // 找到了 nums[middle] == target
                // 开始第二轮 查询
                // 1. begin ~ middle 之间
                // (注意边界条件  pe1 = middle 而不是 middle + 1, 因为必须要保证该位置值为target)
                int pb1 = begin, pe1 = middle, pm1 = middle;
                while (pb1 <= pe1) {
                    pm1 = pb1 + (pe1 - pb1) / 2;
                    if (nums[pm1] < target) {
                        pb1 = pm1 + 1;
                    } else {// nums[pm1] == target
                        if (pm1 == pb1 || (pm1 > pb1 && nums[pm1 - 1] < target)) {
                            break;// 找到 pm1 是左边第一个==target的元素位置
                        } else {// pm1 > pb1 && nums[pm1 - 1] == target
                            pe1 = pm1 - 1;
                        }
                    }
                }
                // 2. middle ~ end 之间
                // (注意边界条件  pb2 = middle 而不是 middle - 1 因为必须要保证该位置值为target)
                int pb2 = middle, pe2 = end, pm2 = middle;
                while (pb2 <= pe2) {
                    pm2 = pb2 + (pe2 - pb2) / 2;
                    if (nums[pm2] > target) {
                        pe2 = pm2 - 1;
                    } else {// nums[pm2] == target
                        if (pm2 == pe2 || (pm2 < pe2 && nums[pm2 + 1] > target)) {
                            break;// 找到 pm2 是右边最后一个==target的元素位置
                        } else {// pm2 < pe2 && nums[pm2 + 1] == target
                            pb2 = pm2 + 1;
                        }
                    }
                }
                return pm2 - pm1 + 1;
            }
        }

        return 0;
    }

    public void reverse(int[] nums) {
        for (int p1 = 0, p2 = nums.length - 1; p1 < p2; p1++, p2--) {
            swap(nums, p1, p2);
        }
    }

    public void swap(int[] nums, int p1, int p2) {
        int tmp = nums[p1];
        nums[p1] = nums[p2];
        nums[p2] = tmp;
    }

    @Test
    public void test() {
        Assert.assertEquals(1, search(new int[]{5,7,7,8,8,10}, 5));
        Assert.assertEquals(1, search(new int[]{1,2,3}, 2));
    }

}
