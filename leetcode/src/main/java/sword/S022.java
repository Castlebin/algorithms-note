package sword;

import org.junit.Test;
import common.ListNode;

/**
 面试题22. 链表中倒数第k个节点

 输入一个链表，输出该链表中倒数第k个节点。

 为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
 例如，一个链表有6个节点，从头节点开始，它们的值依次是1、2、3、4、5、6。
 这个链表的倒数第3个节点是值为4的节点。
 */
public class S022 {

    public ListNode getKthFromEnd(ListNode head, int k) {
        if (k <= 0) {
            throw new IllegalArgumentException("k must >= 1");
        }
        if (head == null) {
            return null;
        }
        int length = 0;
        ListNode cur = head;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        if (k > length) {
            return null;
        }
        // 倒数第 k 个，不就是 顺数 第 length + 1 - k 个吗？
        cur = head;
        int count = 0;
        while (cur != null) {
            count++;
            if (count == length + 1 - k) {
                return cur;
            }
            cur = cur.next;
        }
        return null;
    }

    /**
     * 快慢指针解法，一次遍历
     */
    public ListNode getKthFromEnd_2(ListNode head, int k) {
        if (k <= 0) {
            throw new IllegalArgumentException("k must >= 1");
        }

        ListNode p1 = head;
        int count = 0;
        while (p1 != null && count < k) {
            count++;
            p1 = p1.next;
        }
        if (count < k) {
            return null;
        }
        ListNode p2 = head;
        while (p1 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }

        return p2;
    }


    @Test
    public void test() {

    }

}
