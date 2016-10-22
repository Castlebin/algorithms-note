package linkedlist;

import lombok.Data;

// 定义好链表节点数据结构
@Data
class Node {
    int data;
    Node next;

    public void printNode() {
        System.out.println(data);
        if (next != null) {
            next.printNode();
        }
    }

    public Node deepCopy() {
        Node head = new Node();
        head.data = data;
        return deepCopy(head, next);
    }

    public Node deepCopy(Node last, Node nowToCopy) {
        if (nowToCopy != null) {
            Node node = new Node();
            node.data = nowToCopy.data;
            last.next = node;
            deepCopy(node, nowToCopy.next);
        }

        return last;
    }

    public static Node buildTestListedList(int length) {
        if (length <= 0) {
            return null;
        }
        Node head = new Node();
        head.setData(0);
        Node lastNode = head;
        for (int i = 1; i < length; i++) {
            Node node = new Node();
            node.setData(i);
            lastNode.setNext(node);
            lastNode = node;
        }

        return head;
    }
}

public class LinkedListTest {
    public static void main(String[] args) {
        Node linkedList1 = Node.buildTestListedList(5);
        linkedList1.printNode();
        Node linkedList2 = linkedList1.deepCopy();
        linkedList2.printNode();
    }
}
