package common;

import java.util.LinkedList;
import java.util.Queue;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int x) {
        val = x;
    }

    public static TreeNode buildBinaryTreeFromArray(Integer[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        for (int index = 1; index < arr.length; ) {
            TreeNode parent = queue.remove();
            Integer leftValue = arr[index];
            if (leftValue != null) {
                TreeNode left = new TreeNode(leftValue);
                parent.left = left;
                queue.add(left);
            }
            index++;
            if (index < arr.length) {
                Integer rightValue = arr[index];
                if (rightValue != null) {
                    TreeNode right = new TreeNode(rightValue);
                    parent.right = right;
                    queue.add(right);
                }
            }
            index++;
        }

        return root;
    }

    public static TreeNode findTreeNodeByValueRecursively(TreeNode root, int value) {
        if (root != null) {
            if (root.val == value) {
                return root;
            }
            TreeNode node = findTreeNodeByValueRecursively(root.left, value);
            if (node != null) {
                return node;
            }
            return findTreeNodeByValueRecursively(root.right, value);
        }
        return null;
    }

    public static TreeNode findBSTNodeByValueRecursively(TreeNode bst, int value) {
        if (bst != null) {
            if (bst.val == value) {
                return bst;
            }
            if (value < bst.val) {
                return findBSTNodeByValueRecursively(bst.left, value);
            } else {
                return findBSTNodeByValueRecursively(bst.right, value);
            }
        }
        return null;
    }

}
