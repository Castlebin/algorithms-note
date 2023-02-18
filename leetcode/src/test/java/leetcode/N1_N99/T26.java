package leetcode.N1_N99;

import org.junit.Assert;
import org.junit.Test;


/**
 * 26. Remove Duplicates from Sorted Array
 * 26. 删除有序数组中的重复项
 */
public class T26 {

    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // 两个指针，一块一慢，快的指针寻找和慢指针指向位置不同的元素，直到快指针遍历完数组
        int slow = 0, fast = 0;
        while (fast < nums.length) {
            // fast 指针找到一个新的不重复的元素了
            if (nums[fast] != nums[slow]) {
                nums[++slow] = nums[fast];
            }
            fast++;
        }
        // slow 指向的是最后一个不重复的元素位置，所以，不重复的元素长度应该是 slow + 1
        return slow + 1;
    }

    @Test
    public void test() {
        Assert.assertEquals(2, removeDuplicates(new int[] {1, 1, 2}));
        Assert.assertEquals(5, removeDuplicates(new int[] {0, 0, 1, 1, 1, 2, 2, 3, 3, 4}));
    }

}
