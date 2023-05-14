package sword.sa;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * 剑指 Offer 35. 复杂链表的复制
 *
 * 请实现 copyRandomList 函数，复制一个复杂链表。
 * 在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，还有一个 random 指针指向链表中的任意节点或者 null。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/fu-za-lian-biao-de-fu-zhi-lcof
 */
public class T035 {

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

    public Node copyRandomList(Node head) {
        if (head == null) {
            return head;
        }
        // 思路：先复制节点（用 map 保存老节点和新节点的关系）,再复制指针
        Map<Node, Node> map = new HashMap<>();
        Node cur = head;
        // 先复制节点
        while (cur != null) {
            Node newCur = new Node(cur.val);
            map.put(cur, newCur);
            cur = cur.next;
        }
        // 再复制指针
        cur = head;
        while (cur != null) {
            Node newCur = map.get(cur);
            newCur.next = map.get(cur.next);
            newCur.random = map.get(cur.random);
            cur = cur.next;
        }

        return map.get(head);
    }

    @Test
    public void test() {

    }

}
