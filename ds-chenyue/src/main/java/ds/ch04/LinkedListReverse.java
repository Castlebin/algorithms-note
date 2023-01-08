package ds.ch04;

import org.junit.Test;

public class LinkedListReverse {

    class Node {
        int data;
        Node next;

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            Node cur = this;
            while (cur != null) {
                sb.append(cur.data).append("->");
                cur = cur.next;
            }
            sb.append("null");
            return sb.toString();
        }
    }

    public static Node reverse(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node prev = null;
        Node cur = head;
        while (cur != null) {
            Node next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }

        return prev;
    }

    @Test
    public void testReverse() {
        for (int n = 0; n < 10; n++) {
            Node head = new Node();
            head.data = 0;
            Node cur = head;
            for (int i = 1; i <= n; i++) {
                Node next = new Node();
                next.data = i;

                cur.next = next;
                cur = next;
            }

            System.out.println(head);
            System.out.println(reverse(head));
            System.out.println("-----------------");
        }
    }

}
