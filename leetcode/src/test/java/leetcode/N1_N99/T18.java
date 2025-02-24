package leetcode.N1_N99;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

/**
 * 18. 四数之和
 * 给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。
 * 请你找出并返回满足下述全部条件且**不重复**的四元组 [nums[a], nums[b], nums[c], nums[d]] ：
 * 0 <= a, b, c, d < n
 * a、b、c 和 d 互不相同
 * nums[a] + nums[b] + nums[c] + nums[d] == target
 * 你可以按 任意顺序 返回答案 。
 * https://leetcode-cn.com/problems/4sum/
 */
public class T18 {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        return kSum(nums, target, 4);
    }

    /**
     * k 数之和
     *
     * @param nums 数组
     * @param target 目标值
     * @param k k 个数
     * @return 所有的解
     */
    List<List<Integer>> kSum(int[] nums, int target, int k) {
        Arrays.sort(nums); // 先排序
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> trace = new LinkedList<>();
        backtrace(nums, target, k, 0, trace, res);
        return res;
    }

    void backtrace(int[] nums, long target, int k,
            int start, LinkedList<Integer> trace, List<List<Integer>> res) {
        if (k == 2) { // 两数之和。base case
            List<List<Integer>> twoSumRes = twoSum(nums, target, start);
            for (List<Integer> t : twoSumRes) {
                List<Integer> r = new ArrayList<>(trace);
                r.addAll(t);
                res.add(r);
            }
            return;
        }

        // 回溯
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            trace.add(nums[i]);
            backtrace(nums, target - nums[i], k - 1, i + 1, trace, res);
            trace.removeLast();
        }
    }

    /**
     * 两数之和 (返回所有不重复的解)
     * @param nums 数组
     * @param target 目标值
     * @param start 开始下标
     * @return 所有的解 (不重复)
     */
    List<List<Integer>> twoSum(int[] nums, long target, int start) {
        List<List<Integer>> res = new ArrayList<>();
        int left = start, right = nums.length - 1;
        while (left < right) {
            long sum = nums[left] + nums[right];
            if (sum == target) {
                res.add(Arrays.asList(nums[left], nums[right]));
                left++;
                right--;
                // 防止重复的解
                while (left < nums.length && nums[left] == nums[left - 1]) {
                    left++;
                }
            } else if (sum > target) {
                right--;
            } else if (sum < target) {
                left++;
            }
        }
        return res;
    }

    @Test
    public void test() {
        System.out.println(fourSum(new int[] {1, 0, -1, 0, -2, 2}, 0));
        System.out.println(fourSum(new int[] {2, 2, 2, 2, 2}, 8));
    }

}
