package leetcode.base;

/**
 * 单向链表（节点）
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(""+val);
        ListNode next = this.next;
        while (next != null) {
            sb.append(", " + next.val);
            next = next.next;
        }
        return sb.toString();
    }

}
