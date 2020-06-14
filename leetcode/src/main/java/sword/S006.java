package sword;

import org.junit.Test;

/**
 * 06. 从尾到头打印链表
 * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 * <p>
 * 限制：
 * <p>
 * 0 <= 链表长度 <= 10000
 */
public class S006 {

    public int[] reversePrint(ListNode head) {
        if (head == null) {
            return new int[]{};
        }

        int length = 0;
        ListNode cur = head;
        while (cur != null) {
            length++;
            cur = cur.next;
        }

        int[] result = new int[length];
        cur = head;
        while (cur != null) {
            result[--length] = cur.val;
            cur = cur.next;
        }
        return result;
    }

    @Test
    public void test() {

    }

}


class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    public static ListNode createListNode(String s) {
        ListNode head = new ListNode(0);
        ListNode cur = head;
        ListNode next;
        for (int i = 0; i < s.length(); i++) {
            cur.val = Integer.parseInt(s.substring(i, i + 1));
            if (i < s.length() - 1) {
                next = new ListNode(0);
                cur.next = next;
                cur = next;
            }
        }
        return head;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        ListNode cur = this;
        do {
            sb.append(cur.val);
            cur = cur.next;
        } while (cur != null);

        return sb.toString();
    }

}
