package leetcode.N1_N99;

import org.junit.Assert;
import org.junit.Test;

import leetcode.base.ListNode;
import leetcode.base.ListNodeUtil;


/**
 * 19. Remove Nth Node From End of List
 * 19. 删除链表的倒数第 n 个结点
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 */
public class T19 {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast = head;
        int k = 0;
        while (k < n && fast != null) {
            fast = fast.next;
            k++;
        }
        // 要么 fast == null 了，要么 k == n 了
        if (k < n) {
            return head; // 说明 链表长度小于 n
        }
        if (fast == null) {
            // fast == null && k == n ，所以，要删除的节点是 head 节点
            return head.next;
        }
        // 链表长度大于 n , 要删除的是一个中间节点
        ListNode cur = head, prev = null;
        while (fast != null) {
            fast = fast.next;
            prev = cur;
            cur = cur.next;
        }
        prev.next = cur.next;
        return head;
    }

    @Test
    public void test() {
        Assert.assertNull(removeNthFromEnd(ListNodeUtil.buildLinkedList(new int[] {1}, -1), 1));
        System.out.println(removeNthFromEnd(ListNodeUtil.buildLinkedList(new int[] {1, 2, 3}, -1), 1));
    }

}
