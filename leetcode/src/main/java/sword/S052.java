package sword;

import org.junit.Test;
import common.ListNode;

/**
 剑指 Offer 52. 两个链表的第一个公共节点

 输入两个链表，找出它们的第一个公共节点。

 注意：
 1. 如果两个链表没有交点，返回 null.
 2. 在返回结果后，两个链表仍须保持原有的结构。
 3. 可假定整个链表结构中没有循环。
 4. 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
 本题与主站 160 题相同：https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
*/
public class S052 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p1 = headA;
        ListNode p2 = headB;
        while (p1 != p2) {
            p1 = p1 == null? headB : p1.next;
            p2 = p2 == null? headA : p2.next;
        }
        return p1;
    }

    @Test
    public void test() {

    }

}
