package common;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
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
