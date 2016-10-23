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

    public static Node deepCopy2(Node node) {
        Node newHead = null;
        Node lastNewNode = null;
        Node currentNode = node;
        while (currentNode != null) {
            Node newNode = new Node();
            newNode.data = currentNode.data;
            if (lastNewNode != null) {
                lastNewNode.next = newNode;
            } else {
                newHead = newNode;
            }
            currentNode = currentNode.next;
            lastNewNode = newNode;
        }

        return newHead;
    }
    
    public static Node reverse(Node head) {
        if (head == null) {return null;}
        if (head.next == null) {return head;}

        Node lastCursor = null;
        Node cursor = head;
        Node cursorNext = head.next;
        while (cursorNext != null) {
            // 执行反转
            cursor.next = lastCursor;
            // 移动指针
            lastCursor = cursor;
            cursor = cursorNext;
            cursorNext = cursorNext.next;
        }
        // 执行最后一次反转
        cursor.next = lastCursor;
        return cursor;
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
            throw new  IllegalArgumentException("length must bigger then 0");
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
        Node listedList = Node.buildTestListedList(4);
        listedList.printNode();
        System.out.println("---------------------");
        listedList.deepCopy().printNode();
        System.out.println("---------------------");
        Node.deepCopy(listedList).printNode();
        System.out.println("---------------------");
        Node.deepCopy2(listedList).printNode();
        System.out.println("-----------------------");
        Node.reverse(listedList).printNode();
    }
}
