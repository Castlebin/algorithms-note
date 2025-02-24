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
        return nSum(nums, target, 4);
    }

    /**
     * 一举解决 N 数之和问题
     * @param nums 数组
     * @param target 目标值
     * @param n 几个数？
     * @return 所有的解
     */
    public List<List<Integer>> nSum(int[] nums, int target, int n) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        backtrace(nums, 0, target, n, new LinkedList<>(), res);
        return res;
    }

    /**
     * 回溯
     * @param nums 数组
     * @param start 起始下标
     * @param target 目标和 （防止大数之和溢出，改为 long）
     * @param k 几个数？
     * @param trace 路径
     * @param res 结果集
     */
    void backtrace(int[] nums, int start, long target, int k, LinkedList<Integer> trace, List<List<Integer>> res) {
        if (k == 2) { // 排序数组的两数之和
            List<int[]> pos = twoSum(nums, start, target);
            for (int[] p : pos) {
                trace.addLast(nums[p[0]]);
                trace.addLast(nums[p[1]]);
                res.add(new ArrayList<>(trace));
                trace.removeLast();
                trace.removeLast();
            }
            return;
        }
        for (int i = start; i < nums.length; i++) {
            // 剪枝。如果当前数字和上一个数字相同，跳过。防止重复的解 （因为这意味着，当前数字和上一个数字的解是一样的）
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            trace.addLast(nums[i]);
            backtrace(nums, i + 1, target - nums[i], k - 1, trace, res);
            trace.removeLast();
        }
    }

    /**
     * 排序数组的两数之和 （从 start 下标开始）
     * @param nums 排序数组
     * @param start 起始位置
     * @param target 目标值  (防止大数之和溢出，所以类型用 long)
     * @return 两数之和的下标 ，所有的解 (不重复）
     */
    List<int[]> twoSum(int[] nums, int start, long target) {
        List<int[]> res = new ArrayList<>();
        int left = start, right = nums.length - 1;
        while (left < right) {
            long sum = nums[left] + nums[right];
            if (sum == target) {
                // 其实也可以在这里处理重复。但我选择在最后处理
                /** 在这里处理的方式
                 if (left > start && nums[left] == nums[left - 1]) {
                     left++;
                     continue;
                 }
                 */
                res.add(new int[]{left, right});
                left++;
                right--;
                // 在这里处理重复，防止下一个解和当前解重复  (效率比在上面要略高)
                while (left < nums.length && nums[left] == nums[left - 1]) {
                    left++;
                }
                // 找到了一个解，但是可能还有解，所以这儿也不返回，因为我们要的是所有的解 (不重复)
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return res;
    }

    @Test
    public void test() {
        System.out.println(fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0));
        System.out.println(fourSum(new int[]{2, 2, 2, 2, 2}, 8));
    }

}
