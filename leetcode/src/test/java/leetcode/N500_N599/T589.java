package leetcode.N500_N599;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * 590. N 叉树的后序遍历
 */
public class T589 {
    // 解法 1 : 递归，使用辅助函数
    public List<Integer> preorder(Node root) {
        List<Integer> res = new ArrayList<>();
        preorder(root, res);
        return res;
    }
    public void preorder(Node root, List<Integer> res) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        for (Node node : root.children) {
            preorder(node, res);
        }
    }

    // 解法 2 : 递归，没有使用辅助函数
    public List<Integer> preorder_2(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        res.add(root.val);
        for (Node node : root.children) {
            res.addAll(preorder(node));
        }
        return res;
    }

    @Test
    public void test() {
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n3 = new Node(3, Arrays.asList(n5, n6));
        Node n2 = new Node(2);
        Node n4 = new Node(4);
        Node root = new Node(1, Arrays.asList(n3, n2, n4));

        System.out.println(preorder(root));
        System.out.println(preorder_2(root));
    }


    // Definition for a Node.
    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

}
