package sword;

import org.junit.Test;

/**
 剑指 Offer 35. 复杂链表的复制
 请实现 copyRandomList 函数，复制一个复杂链表。

 在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，还有一个 random 指针指向链表中的任意节点或者 null。
 */
public class S035 {

    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        // 第 1 步，复制每个节点一份放在自己后面
        Node cur = head;
        while (cur != null) {
            Node next = cur.next;
            Node copy = new Node(cur.val);
            cur.next = copy;
            copy.next = next;
            cur = next;
        }
        // 第 2 步，构建节点之间的random指针
        Node newHead = head.next;
        Node origin = head;
        while (origin != null) {
            Node copy = origin.next;
            if (origin.random != null) {
                copy.random = origin.random.next;
            }
            origin = copy.next;
        }
        // 第 3 步，拆分出新链表
        origin = head;
        while (origin != null) {
            Node copy = origin.next;
            Node originNext = copy.next;
            origin.next = originNext;
            if (originNext != null) {
                copy.next = originNext.next;
            }
            origin = originNext;
        }

        return newHead;
    }

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }



    @Test
    public void test() {

    }

}
