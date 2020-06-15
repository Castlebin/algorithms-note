package sword;

import org.junit.Test;

import java.util.*;

public class S032 {

    /**
     * 面试题32 - I. 从上到下打印二叉树
     */
    public int[] levelOrder(TreeNode root) {
        if (root == null) {
            return new int[]{};
        }
        List<Integer> levelOrderList = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();
            levelOrderList.add(node.val);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        int[] result = new int[levelOrderList.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = levelOrderList.get(i);
        }
        return result;
    }

    /**
     * 面试题32 - II. 从上到下打印二叉树 II
     */
    public List<List<Integer>> levelOrder_2(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int level = 0;
        TreeNode lastTail = root;
        TreeNode thisTail = null;
        List<Integer> thisLevelResult = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();
            thisLevelResult.add(node.val);
            if (node.left != null) {
                queue.add(node.left);
                thisTail = node.left;
            }
            if (node.right != null) {
                queue.add(node.right);
                thisTail = node.right;
            }
            if (node == lastTail) {
                level++;
                lastTail = thisTail;
                result.add(thisLevelResult);
                thisLevelResult = new ArrayList<>();
            }
        }
        return result;
    }

    /**
     * 面试题32 - III. 从上到下打印二叉树 III
     */
    public List<List<Integer>> levelOrder_3(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean reverse = false;
        TreeNode lastTail = root;
        TreeNode thisTail = null;
        List<Integer> thisLevelResult = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();
            thisLevelResult.add(node.val);
            if (node.left != null) {
                queue.add(node.left);
                thisTail = node.left;
            }
            if (node.right != null) {
                queue.add(node.right);
                thisTail = node.right;
            }
            if (node == lastTail) {
                lastTail = thisTail;
                if (reverse) {
                    Collections.reverse(thisLevelResult);
                }
                result.add(thisLevelResult);
                thisLevelResult = new ArrayList<>();
                reverse = !reverse;
            }
        }
        return result;
    }

    @Test
    public void test() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        levelOrder_3(root);
    }

}
