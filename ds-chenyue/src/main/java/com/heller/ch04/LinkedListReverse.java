package com.heller.ch04;

import org.junit.Test;

public class LinkedListReverse {

    class Node {
        int data;
        Node next;

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder(data+"->");
            Node n = next;
            while (n != null) {
                sb.append(n.data).append("->");
                n = n.next;
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
        Node head = new Node();
        head.data = 0;
        Node cur = head;
        for (int i = 1; i < 10; i++) {
            Node next = new Node();
            next.data = i;

            cur.next = next;
            cur = next;
        }

        System.out.println(head);
        System.out.println(reverse(head));
    }

}
