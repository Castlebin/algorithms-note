package ds.ch02.exe;

import java.util.*;

/**
 * 链表 K 旋转
 * Given a constant K and a singly linked list L,
 * you are supposed to reverse the links of every K elements on L.
 *
 * For example, given L being 1→2→3→4→5→6, if K=3,
 * then you must output 3→2→1→6→5→4;
 * if K=4, you must output 4→3→2→1→5→6.
 */
public class ReverseK {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String firstLine = sc.nextLine();
        String[] meta = firstLine.split("\\s+");
        String headAddress = meta[0];
        int size = Integer.parseInt(meta[1]);
        int k = Integer.parseInt(meta[2]);
        Node head = null;
        Map<String, Node> nodeMap = new HashMap<>();
        for (int i = 0; i < size; i++) {
            String line = sc.nextLine();
            String[] nodeData = line.split("\\s+");
            Node node = new Node();
            node.address = nodeData[0];
            node.data = Integer.parseInt(nodeData[1]);
            node.nextAddress = nodeData[2];
            nodeMap.put(node.address, node);
            if (node.address.equals(headAddress)) {
                head = node;
            }
        }
        Node cur = head;
        while (!cur.nextAddress.equals("-1")) {
            cur.next = nodeMap.get(cur.nextAddress);
            cur = cur.next;
        }
        // 链表构造完毕
        Node reversed = reverseK(head, k);
        print(reversed);
    }

    public static int size(Node head) {
        int size = 0;
        Node cur = head;
        while (cur != null) {
            size++;
            cur = cur.next;
        }
        return size;
    }

    public static Node reverseK(Node head, int k) {
        int size = size(head);
        if (size < k || k == 1) {
            return head;
        }
        // 第一个 K 旋转，决定 返回的 head
        Node prev = null;
        Node cur = head;
        for (int i = 0; i < k; i++) {
            Node next = cur.next;
            cur.next = prev;
            cur.nextAddress = prev == null? "-1":prev.address;
            prev = cur;
            cur = next;
        }
        Node result = prev;
        Node tmpTail = head;

        for (int i = k; i + k <= size; i += k) {
            Node nextTail = cur;
            for (int t = 0; t < k; t++) {
                Node next = cur.next;
                cur.next = prev;
                cur.nextAddress = prev.address;
                prev = cur;
                cur = next;
            }
            tmpTail.next = prev;
            tmpTail.nextAddress = prev.address;
            tmpTail = nextTail;
        }
        tmpTail.next = cur;
        tmpTail.nextAddress = cur == null?"-1":cur.address;

        return result;
    }

    static class Node {
        String address;
        int data;
        String nextAddress;
        Node next;
    }

    public static void print(Node head) {
        Node cur = head;
        while (cur != null) {
            System.out.println(cur.address + " " + cur.data + " " + cur.nextAddress);
            cur = cur.next;
        }
    }
}
