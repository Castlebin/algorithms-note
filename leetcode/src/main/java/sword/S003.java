package sword;

import org.junit.Assert;
import org.junit.Test;

/**
 找出数组中重复的数字。

 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。
 数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。
 请找出数组中任意一个重复的数字。

 示例 1：

 输入：
 [2, 3, 1, 0, 2, 5, 3]
 输出：2 或 3
  

 限制：

 2 <= n <= 100000

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class S003 {
    public int findRepeatNumber(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (i != nums[i]) {
                int j = nums[i];
                if (nums[j] != nums[i]) {
                    int tmp = nums[j];
                    nums[j] = nums[i];
                    nums[i] = tmp;
                } else {
                    return nums[i];
                }
            }
        }
        return -1;
    }


    /**
     * 变态要求，不使用额外的空间，也不能改变原始数组  （时间换空间  O(NlogN) 时间复杂度 ）
     *
     * 无法找出所有重复的元素，只能找到某一个
     *
     * 但题目描述得改一点点  长度为 N 的数组，数组中数字范围为 [1, n-1]
     *
     * 因为利用的是抽屉原则 （+二分法），如果元素有 N 个，数组中数字可选也是 N 个，不能解出
     *
     * 必须是 N 个抽屉，放 N + 1 个元素
     *
     * @param nums
     * @return
     */
    public int findRepeatNumber_2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int beginNum = 1, endNum = nums.length;
        while (beginNum <= endNum) {
            int middleNum = (endNum - beginNum) / 2 + beginNum;
            int count = countNumsInScope(nums, beginNum, middleNum);
            if (beginNum == middleNum) {
                if (count > 1) {
                    return beginNum;
                } else {
                    break;
                }
            }
            if (count > (middleNum - beginNum + 1)) {
                endNum = middleNum;
            } else {
                beginNum = middleNum + 1;
            }
        }

        return -1;
    }

    /**
     * 计算数组中，处于 指定区间 [begin, end] 之间的数字个数
     * @param nums 数组
     * @param begin 区间起始数字
     * @param end 区间末端数字
     * @return 处于 指定区间 [begin, end] 之间的数字个数
     */
    public int countNumsInScope(int[] nums, int begin, int end) {
        if (begin > end) {
            return 0;
        }
        int count = 0;
        for (int num : nums) {
            if (num >= begin && num <= end) {
                count++;
            }
        }
        return count;
    }

    @Test
    public void testFindRepeatNumber() {
        int[] nums = new int[]{15, 1, 2, 3, 4, 11, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        Assert.assertEquals(11, findRepeatNumber_2(nums));
    }

}
