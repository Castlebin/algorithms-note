package algorithm;

/**
 * 25. Reverse Nodes in k-Group
 */
public class N0025 {

    public ListNode reverseKGroup(ListNode head, int k) {
        int size = size(head);
        if (size < k) {
            return head;
        }
        // 第一个 K 旋转，决定 返回的 head
        ListNode prev = null;
        ListNode cur = head;
        for (int i = 0; i < k; i++) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        ListNode result = prev;
        ListNode tmpTail = head;

        for (int i = k; i + k <= size; i += k) {
            ListNode nextTail = cur;
            for (int t = 0; t < k; t++) {
                ListNode next = cur.next;
                cur.next = prev;
                prev = cur;
                cur = next;
            }
            tmpTail.next = prev;
            tmpTail = nextTail;
        }
        tmpTail.next = cur;

        return result;
    }

    public static int size(ListNode head) {
        int size = 0;
        ListNode cur = head;
        while (cur != null) {
            size++;
            cur = cur.next;
        }
        return size;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

}
