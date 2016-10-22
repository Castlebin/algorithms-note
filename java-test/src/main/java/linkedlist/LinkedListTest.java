package linkedlist;

// 定义好链表节点数据结构
class Node {
    int data;
    Node next;

    public void printNode() {
        System.out.println(data);
        if (next != null) {
            next.printNode();
        }
    }

    public static void printNode(Node node) {
        while (node != null) {
            System.out.println(node.data);
            node = node.next;
        }
    }

    public static Node deepCopy(Node node) {
        if (node == null) {
            return null;
        }
        Node newHead = new Node();
        newHead.data = node.data;
        Node lastNewNode = newHead;
        Node currentNodeToCopy = node.next;
        while (currentNodeToCopy != null) {
            Node newNode = new Node();
            newNode.data = currentNodeToCopy.data;
            lastNewNode.next = newNode;
            currentNodeToCopy = currentNodeToCopy.next;
            lastNewNode = lastNewNode.next;
        }

        return newHead;
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
        head.data = 0;
        Node lastNode = head;
        for (int i = 1; i < length; i++) {
            Node node = new Node();
            node.data = i;
            lastNode.next = node;
            lastNode = node;
        }

        return head;
    }
}

public class LinkedListTest {
    public static void main(String[] args) {
        Node linkedList1 = Node.buildTestListedList(5);
        linkedList1.printNode();
        System.out.println("---------------------");
        Node linkedList2 = linkedList1.deepCopy();
        linkedList2.printNode();
        System.out.println("---------------------");
        Node linkedList3 = Node.deepCopy(linkedList1);
        linkedList3.printNode();
    }
}
