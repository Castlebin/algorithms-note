package leetcode.N1_N99;

import org.junit.Test;

import leetcode.base.ListNode;

/**
 25. K 个一组翻转链表

 两种解法：
 1. 迭代的普通解法
 2. 递归解法（有趣）

 https://leetcode.cn/problems/reverse-nodes-in-k-group/
 */
public class T25 {

    /**
     * 简化版的递归解法，代码更少，更精炼!!
     */
    class Solution_1 {
        public ListNode reverseKGroup(ListNode head, int k) {
            ListNode cur = head;
            int count = 0;
            while (cur != null && count < k) {
                cur = cur.next;
                count++;
            }
            if (count == k) {
                cur = reverseKGroup(cur, k);
                while (count > 0) {
                    count--;
                    ListNode tmp = head.next;
                    head.next = cur;
                    cur = head;
                    head = tmp;
                }
                head = cur;
            }
            return head;
        }
    }

    /**
     * 递归解法。写 K 个一组反转链表，递归也很好用。
     */
    class Solution_2 {
        public ListNode reverseKGroup(ListNode head, int k) {
            int count = 0;
            ListNode prev = null;
            ListNode cur = head;
            while (count < k && cur != null) {
                prev = cur;
                cur = cur.next;
                count++;
            }
            // 不足 k 个
            if (count < k) {
                return head;
            }
            // 足 k 个
            ListNode result = prev; // 保存第一段翻转后的头，这个是结果
            // 翻转
            cur = head;
            prev = null;
            while (cur != null && count > 0) {
                ListNode next = cur.next;
                cur.next = prev;
                prev = cur;
                cur = next;
                count--;
            }
            // 递归的反转后面的段
            head.next = reverseKGroup(cur, k);
            return result;
        }
    }

    @Test
    public void test() {

    }

}
