package leetcode.N200_N299;

import org.junit.Test;

import leetcode.base.ListNode;

/**
 206. 反转链表

 两种解法：
 1. 迭代的普通解法
 2. 递归解法（有趣）

 https://leetcode.cn/problems/reverse-linked-list/
 */
public class T206 {

    class Solution_1 {
        public ListNode reverseList(ListNode head) {
            ListNode prev = null;
            ListNode cur = head;
            while (cur != null) {
                ListNode next = cur.next;
                cur.next = prev;
                prev = cur;
                cur = next;
            }
            return prev;
        }
    }

    /**
     * 递归解法。写 K 个一组反转链表，递归也很好用。
     */
    class Solution_2 {
        public ListNode reverseList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode next = head.next;
            ListNode newHead = reverseList(next);
            head.next = null;
            next.next = head;
            return newHead;
        }
    }

    @Test
    public void test() {

    }

}
