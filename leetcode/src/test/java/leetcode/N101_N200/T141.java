package leetcode.N101_N200;

import org.junit.Assert;
import org.junit.Test;

import leetcode.base.ListNode;

/**
 * 141. Linked List Cycle     环形链表
 * <p>
 * 给你一个链表的头节点 head ，判断链表中是否有环。
 */
public class T141 {

    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        // 双指针（快慢指针），一个指针每次移动一个节点，一个移动两个节点。若干两个指针相遇了，说明链表有环
        ListNode p1 = head;
        ListNode p2 = head;
        while (p1 != null && p2 != null) {
            p1 = p1.next;
            p2 = p2.next;
            if (p2 != null) {
                p2 = p2.next;
            } else {
                return false;
            }
            if (p1 == p2) {
                return true;
            }
        }

        return false;
    }

    @Test
    public void test() {
        ListNode l1 = ListNode.buildLinkedList(new int[] {3, 2, 0, -4}, 1);
        Assert.assertEquals(true, hasCycle(l1));

        ListNode l2 = ListNode.buildLinkedList(new int[] {1, 2}, 0);
        Assert.assertEquals(true, hasCycle(l2));

        ListNode l3 = ListNode.buildLinkedList(new int[] {1, 2}, -1);
        Assert.assertEquals(false, hasCycle(l3));

        ListNode l4 = ListNode.buildLinkedList(new int[] {1}, -1);
        Assert.assertEquals(false, hasCycle(l4));

        ListNode l5 = ListNode.buildLinkedList(new int[] {1}, 0);
        Assert.assertEquals(true, hasCycle(l5));
    }

}
