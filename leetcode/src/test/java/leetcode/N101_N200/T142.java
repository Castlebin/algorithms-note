package leetcode.N101_N200;

import org.junit.Assert;
import org.junit.Test;

import leetcode.base.ListNode;
import leetcode.base.ListNodeUtil;

/**
 * 142. Linked List Cycle II
 * 142. 环形链表 II
 * <p>
 * 给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null
 */
public class T142 {

    public ListNode detectCycle(ListNode head) {
        // 定义快慢指针，开始事都指向链表头部
        ListNode fast = head, slow = head;
        // 只要快指针能往下继续走，那么就一直往下走，每次快指针走 2 步，慢指针 1 步
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            // 如果 快、慢指针相遇，说明有环
            if (fast == slow) {
                // 重置慢指针位置到 head
                slow = head;
                // 两个指针现在每次都只往下移动 1 步，相遇位置即是环的入口
                while (fast != slow) {
                    fast = fast.next;
                    slow = slow.next;
                }
                // 相遇位置即是环的入口
                return slow;
            }
        }
        // 不能往下走了，说明没有环
        return null;
    }

    @Test
    public void test() {
        ListNode l1 = ListNodeUtil.buildLinkedList(new int[] {3, 2, 0, -4}, 1);
        Assert.assertEquals(ListNodeUtil.getNode(l1, 1), detectCycle(l1));

        ListNode l2 = ListNodeUtil.buildLinkedList(new int[] {1, 2}, 0);
        Assert.assertEquals(ListNodeUtil.getNode(l2, 0), detectCycle(l2));

        ListNode l3 = ListNodeUtil.buildLinkedList(new int[] {1, 2}, -1);
        Assert.assertEquals(null, detectCycle(l3));

        ListNode l4 = ListNodeUtil.buildLinkedList(new int[] {1}, -1);
        Assert.assertEquals(null, detectCycle(l4));

        ListNode l5 = ListNodeUtil.buildLinkedList(new int[] {1}, 0);
        Assert.assertEquals(ListNodeUtil.getNode(l5, 0), detectCycle(l5));
    }

}
