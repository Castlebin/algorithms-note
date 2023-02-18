package leetcode.N1_N99;

import org.junit.Test;

import leetcode.base.ListNode;

/**
 * 21. Merge Two Sorted Lists
 * 21. 合并两个有序链表
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 */
public class T21 {

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(-1), p = dummy;
        ListNode p1 = list1, p2 = list2;
        // 依次访问两个链表，哪个指针指向的值小，就用哪个，并且，移动该指针
        while (p1 != null && p2 != null) {
            if (p1.val <= p2.val) {
                p.next = p1;
                p1 = p1.next;
            } else {
                p.next = p2;
                p2 = p2.next;
            }
            // p 指针也要往下移动，指向新链表最新的这个节点!!注意别忘了!!
            p = p.next;
        }
        // 说明有一个已经访问完了，那么将另一个直接挂到 p 的后面即可
        if (p1 != null) {
            p.next = p1;
        } else {
            p.next = p2;
        }

        return dummy.next;
    }

    @Test
    public void test() {

    }

}
