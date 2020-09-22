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

        /**
         * 很容易出错，画图吧
         *
         * 指针操作挺麻烦的，全靠画图（程序应该还可以简化，但是这样还是挺清晰的）
         */
        public Node treeToDoublyList(Node root) {
            if (root == null) {
                return root;
            }
            if (root.left == null && root.right == null) {
                root.left = root;
                root.right = root;
                return root;
            }
            // root 至少有一个儿子
            Node leftChild = root.left;
            Node rightChild = root.right;
            if (leftChild != null && rightChild != null) {
                Node left = treeToDoublyList(root.left);
                root.left = left.left;
                left.left.right = root;

                Node right = treeToDoublyList(root.right);
                root.right = right;

                left.left = right.left;

                right.left.right = left;
                right.left = root;

                return left;
            } else if (leftChild != null) {
                Node left = treeToDoublyList(root.left);
                root.left = left.left;
                left.left.right = root;

                left.left = root;
                root.right = left;

                return left;
            } else {
                Node right = treeToDoublyList(root.right);
                root.right = right;
                root.left = right.left;

                right.left.right = root;
                right.left = root;

                return root;
            }
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
        Solution.Node node = solution.treeToDoublyList(solution.buildBinaryTreeFromArray(new Integer[]{4, 2, 5, 1, 3}));
        System.out.println(node);
    }

}
