package leetcode.N1_N99;

import org.junit.Test;

import leetcode.base.ListNode;


/**
 * 19. Remove Nth Node From End of List
 * 19. 删除链表的倒数第 N 个结点
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 */
public class T19 {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        // 双指针 p1、p2 开始都指向头结点。然后 p1 先走 n 步。这样两个指针就相隔 n 个节点了
        ListNode p1 = head, p2 = head, p2Pre = null;
        for (int index = 0; index < n && p1 != null; index++) {
            p1 = p1.next;
        }
        // 这里，两个指针开始同步走，这样 p1 到达尾部(== null), p2 即是倒数第 N 个节点
        while (p1 != null) {
            p1 = p1.next;
            p2Pre = p2;
            p2 = p2.next;
        }
        // p1 == null 了，p2 指向的是倒数第 n 个节点
        // 如果要删除的是头节点，返回会变化，否则返回的就是原来的头结点
        if (p2 == head) {
            return head.next;
        }
        // 正常删除倒数第 n 个节点，简单的改下指针就行 (如果 C 这种无垃圾回收的语言，还应该注意节点的内存回收)
        p2Pre.next = p2.next;

        return head;
    }

    @Test
    public void test() {

    }

}
