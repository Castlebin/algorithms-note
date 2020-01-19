package ds.ch02.linkedlist;

import org.junit.Test;

public class ReverseLinkedList {

    /**
     * 单向链表 反转
     */
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

    /**
     * 单向链表，每k个翻转。从头开始划分，划分不足k个，则该段不翻转
     */
    public static Node reverseK(Node head, int k) {
        return null;
    }

    @Test
    public void testReverse() {
        Node origin = NodeUtil.generateLinkedListFromArray(new int[] {1,2,3,4,5,6,7});
        System.out.println("origin : " + NodeUtil.toString(origin));
        Node reversed = reverse(origin);
        System.out.println("reversed : " + NodeUtil.toString(reversed));
    }

}
