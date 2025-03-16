package leetcode.N1_N99;

import leetcode.base.ListNode;

/**
 * 92. 反转链表 II
 * https://leetcode.cn/problems/reverse-linked-list-ii/
 */
public class T92 {

    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == right) {
            return head;
        }
        // 创建一个虚拟头结点，方便处理边界情况
        ListNode dummy = new ListNode();
        dummy.next = head;

        ListNode prev = dummy, cur = head;
        int count = 1;
        ListNode lf = null, lp = null;
        ListNode rt = null, rn = null;
        while (cur != null) {
            if (count == left) {
                lf = cur;
                lp = prev;
                System.out.println("left: " + lf.val);
            } else if (count == right) {
                rt = cur;
                rn = cur.next;
                System.out.println("right: " + rt.val);
                break;
            }
            prev = cur;
            cur = cur.next;
            count++;
        }

        // 将 left 和 right 之间的链表 与原链表完全断开  （方便对它直接应用标准的反转链表，也防止后面的操作造成环）
        lp.next = null;
        rt.next = null;

        // 将 left 和 right 之间的链表反转
        ListNode reversed = reverse(lf);

        // 将反转后的链表与原链表连接起来
        lf.next = rn;
        lp.next = reversed;

        return dummy.next;
    }

    /**
     * 标准的 反转链表
     */
    ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

}
