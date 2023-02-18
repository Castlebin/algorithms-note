package leetcode.N800_N899;

import org.junit.Test;

import leetcode.base.ListNode;


/**
 * 876. Middle of the Linked List
 * 876. 链表的中间结点
 * 给定一个头结点为 head 的非空单链表，返回链表的中间结点。
 * 如果有两个中间结点(偶数个)，则返回第二个中间结点。
 */
public class T876 {

    /**
     * 简单用下快慢指针就行了
     */
    public ListNode middleNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    @Test
    public void test() {

    }

}
