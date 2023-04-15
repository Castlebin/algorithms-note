package leetcode.base.tree;

/**
 * 扩展普通的二叉树节点，节点多一个指向它的下一个兄弟的指针
 * https://leetcode.cn/problems/populating-next-right-pointers-in-each-node-ii/?show=1
 */
public class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}
