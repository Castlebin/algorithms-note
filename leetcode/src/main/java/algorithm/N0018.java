package algorithm;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 18. 四数之和

 给定一个包含 n 个整数的数组 nums 和一个目标值 target，
 判断 nums 中是否存在四个元素 a，b，c 和 d ，
 使得 a + b + c + d 的值与 target 相等？
 找出所有满足条件且不重复的四元组。

 注意：
 答案中不可以包含重复的四元组。
 */
public class N0018 {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length <= 3) {
            return ans;
        }
        int n = nums.length;
        Arrays.sort(nums);
        for (int p1 = 0; p1 < n - 3; p1++) {
            // 不能与上一个元素相同，否则会出现重复解
            if (p1 > 0 && nums[p1] == nums[p1 - 1]) {
                continue;
            }
            for (int p2 = p1 + 1; p2 < n - 2; p2++) {
                // 不能与上一个元素相同，否则会出现重复解
                if (p2 > p1 + 1 && nums[p2] == nums[p2 - 1]) {
                    continue;
                }
                int p4 = n - 1;
                int leftTargetSum = target - nums[p1] - nums[p2];
                for (int p3 = p2 + 1; p3 < p4; p3++) {
                    if (p3 > p2 + 1 && nums[p3] == nums[p3 - 1]) {
                        continue;
                    }
                    while (p3 < p4 && nums[p3] + nums[p4] > leftTargetSum) {
                        p4--;
                    }
                    if (p3 == p4) {
                        break;
                    }
                    if (nums[p3] + nums[p4] == leftTargetSum) {
                        List<Integer> match = new ArrayList<>();
                        match.add(nums[p1]);
                        match.add(nums[p2]);
                        match.add(nums[p3]);
                        match.add(nums[p4]);
                        ans.add(match);
                    }
                }
            }
        }

        return ans;
    }

    @Test
    public void test() {

    }

}
