package sword;

import org.junit.Test;

import java.util.*;

/**
 剑指 Offer 36. 二叉搜索树与双向链表

 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。
 要求不能创建任何新的节点，只能调整树中节点指针的指向。
 */
public class S036 {

    class Solution {

        public Node treeToDoublyList(Node root) {
            if (root == null) {
                return root;
            }
            // todo
        }

        public Node findMin(Node root) {
            if (root == null || root.left == null) {
                return root;
            }
            return findMin(root.left);
        }

        public Node findMax(Node root) {
            if (root == null || root.right == null) {
                return root;
            }
            return findMax(root.right);
        }

        class Node {
            public int val;
            public Node left;
            public Node right;

            public Node() {}

            public Node(int _val) {
                val = _val;
            }

            public Node(int _val,Node _left,Node _right) {
                val = _val;
                left = _left;
                right = _right;
            }
        }

        public Node buildBinaryTreeFromArray(Integer[] arr) {
            if (arr == null || arr.length == 0) {
                return null;
            }
            Node root = new Node(arr[0]);
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);

            for (int index = 1; index < arr.length; ) {
                Node parent = queue.remove();
                Integer leftValue = arr[index];
                if (leftValue != null) {
                    Node left = new Node(leftValue);
                    parent.left = left;
                    queue.add(left);
                }
                index++;
                if (index < arr.length) {
                    Integer rightValue = arr[index];
                    if (rightValue != null) {
                        Node right = new Node(rightValue);
                        parent.right = right;
                        queue.add(right);
                    }
                }
                index++;
            }

            return root;
        }
    }

    @Test
    public void test() {
        Solution solution = new Solution();
        solution.treeToDoublyList(solution.buildBinaryTreeFromArray(new Integer[]{4,2,5,1,3}));
    }

}
