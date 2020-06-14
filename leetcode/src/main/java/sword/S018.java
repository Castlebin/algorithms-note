package sword;

import org.junit.Test;

/**
 面试题18. 删除链表的节点
 给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。

 返回删除后的链表的头节点。
 */
public class S018 {

    public ListNode deleteNode(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        if (head.val == val) {
            ListNode result = head.next;
            head.next = null;
            return result;
        }
        ListNode prev = head;
        ListNode cur = prev.next;
        while (cur != null) {
            if (cur.val == val) {
                prev.next = cur.next;
                cur.next = null;
                break;
            }
            prev = cur;
            cur = cur.next;
        }
        return head;
    }

    @Test
    public void test() {

    }

}
