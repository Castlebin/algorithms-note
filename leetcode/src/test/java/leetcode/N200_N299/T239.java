package leetcode.N200_N299;

import java.util.Deque;
import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Test;

/**
 * 239. 滑动窗口最大值
 * https://leetcode-cn.com/problems/sliding-window-maximum/
 * 思路：双端队列
 * 1. 遍历数组，将索引放入双端队列中
 * 2. 判断队列头部索引是否在窗口内，不在则移除
 * 3. 判断队列尾部索引对应的值是否小于当前值，小于则移除
 * 4. 将当前索引放入队列尾部
 * 5. 当索引大于等于 k-1 时，将队列头部索引对应的值放入结果数组
 * 6. 返回结果数组
 */
public class T239 {

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int n = nums.length;
        int[] result = new int[n - k + 1];
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            // 移除窗口外的元素
            while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }
            // 移除比当前元素小的元素
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            // 当窗口形成时，将队列头部元素放入结果数组
            if (i >= k - 1) {
                result[i - k + 1] = nums[deque.peekFirst()];
            }
        }
        return result;
    }

    @Test
    public void test() {
        Assert.assertArrayEquals(new int[]{3, 3, 5, 5, 6, 7}, maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3));
        Assert.assertArrayEquals(new int[]{1}, maxSlidingWindow(new int[]{1}, 1));
        Assert.assertArrayEquals(new int[]{1, -1}, maxSlidingWindow(new int[]{1, -1}, 1));
        Assert.assertArrayEquals(new int[]{11}, maxSlidingWindow(new int[]{9, 11}, 2));
    }

}
