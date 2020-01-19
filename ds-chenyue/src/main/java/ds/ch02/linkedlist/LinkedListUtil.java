package ds.ch02.linkedlist;

public class LinkedListUtil {
    /**
     * 打印单向链表，格式：1->2->3->4->5->
     */
    public static String toString(Node head) {
        if (head == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        Node cur = head;
        while (cur != null) {
            sb.append(cur.data).append("->");
            cur = cur.next;
        }
        return sb.toString();
    }

    /**
     * 根据数组生成一个单向链表
     */
    public static Node generateLinkedListFromArray(int[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        Node head = new Node(array[0]);
        Node cur = head;
        for (int i = 1; i < array.length; i++) {
            Node node = new Node(array[i]);
            cur.next = node;
            cur = node;
        }

        return head;
    }
}
