package leetcode;

class LinkListNode {
    int val;
    LinkListNode next;

    LinkListNode(int val) {
        this.val = val;
    }

    public static LinkListNode create(int[] arr) {
        LinkListNode head = null;
        LinkListNode prev = null;
        for (int var : arr) {
            LinkListNode node = new LinkListNode(var);
            if (head == null) {
                head = node;
            }
            if (prev != null) {
                prev.next = node;
            }
            prev = node;
        }

        return head;
    }

    public LinkListNode reverse() {
        LinkListNode prev = null;
        LinkListNode cur = this;
        while (cur != null) {
            LinkListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        LinkListNode cur = this;
        while (cur.next != null) {
            sb.append(cur.val).append(", ");
            cur = cur.next;
        }
        sb.append(cur.val);

        return sb.toString();
    }

}
