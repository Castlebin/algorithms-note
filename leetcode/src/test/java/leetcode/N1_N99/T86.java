package leetcode.N1_N99;

import org.junit.Test;

import leetcode.base.ListNode;


/**
 * 86. Partition List
 * 86. 分隔链表
 * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
 * <p>
 * 你应当 保留 两个分区中每个节点的初始相对位置。
 */
public class T86 {

    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }
        // 将链表拆分为两个，一个其中所有的元素都小于 x, 另一个所有的元素都大于等于 x
        // 便于操作，都给个哑结点
        ListNode dummy1 = new ListNode(-1), p1 = dummy1;
        ListNode dummy2 = new ListNode(-1), p2 = dummy2;
        ListNode cur = head;
        while (cur != null) {
            if (cur.val < x) {
                p1.next = cur;
                p1 = p1.next;
            } else {
                p2.next = cur;
                p2 = p2.next;
            }
            // !! 注意，这个节点已经用完了，要断开它和原来链表的连接。很容易直接写成 cur = cur.next 了 !!
            ListNode next = cur.next;
            cur.next = null; // !!
            cur = next;
        }
        // 合并两个链表 (不要 dummy2 这个哑结点)
        p1.next = dummy2.next;
        // 不要 dunmy1 这个哑结点
        return dummy1.next;
    }

    @Test
    public void test() {

    }

}
