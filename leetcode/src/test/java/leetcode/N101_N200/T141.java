package leetcode.N101_N200;

import org.junit.Assert;
import org.junit.Test;

import leetcode.base.ListNode;
import leetcode.base.ListNodeUtil;

/**
 * 141. Linked List Cycle
 * 141. 环形链表
 * <p>
 * 给你一个链表的头节点 head ，判断链表中是否有环。
 */
public class T141 {

    public boolean hasCycle(ListNode head) {
        // 快慢指针，开始时，都指向头结点
        ListNode fast = head, slow = head;
        // 只要还能往下走，每次快指针走 2 步，慢指针走 1 步
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            // 快慢指针相遇，说明一定有环
            if (fast == slow) {
                return true;
            }
        }
        // 不能往下走了，那说明没环
        return false;
    }

    @Test
    public void test() {
        ListNode l1 = ListNodeUtil.buildLinkedList(new int[] {3, 2, 0, -4}, 1);
        Assert.assertEquals(true, hasCycle(l1));

        ListNode l2 = ListNodeUtil.buildLinkedList(new int[] {1, 2}, 0);
        Assert.assertEquals(true, hasCycle(l2));

        ListNode l3 = ListNodeUtil.buildLinkedList(new int[] {1, 2}, -1);
        Assert.assertEquals(false, hasCycle(l3));

        ListNode l4 = ListNodeUtil.buildLinkedList(new int[] {1}, -1);
        Assert.assertEquals(false, hasCycle(l4));

        ListNode l5 = ListNodeUtil.buildLinkedList(new int[] {1}, 0);
        Assert.assertEquals(true, hasCycle(l5));
    }

}
