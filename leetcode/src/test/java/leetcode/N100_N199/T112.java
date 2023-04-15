package leetcode.N100_N199;

import static common.TreeUtil.buildTree;

import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Test;

import leetcode.base.TreeNode;

/**
112. 路径总和
给你二叉树的根节点 root 和一个表示目标和的整数 targetSum 。
判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和 targetSum 。
如果存在，返回 true ；否则，返回 false 。

来源：力扣（LeetCode）
链接：https://leetcode.cn/problems/path-sum
 */
public class T112 {
    int sum = 0;
    LinkedList<TreeNode> trace = new LinkedList<>();

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        sum += root.val;
        trace.add(root);
        // 是叶子节点了，看看是否路径和符合要求
        if (root.left == null && root.right == null) {
            if (sum == targetSum) {
                return true;
            }
        }
        boolean leftResult = hasPathSum(root.left, targetSum);
        if (leftResult) {
            return true;
        }
        boolean rightResult = hasPathSum(root.right, targetSum);
        if (rightResult) {
            return true;
        }
        sum -= root.val;
        trace.removeLast();
        return false;
    }

    @Test
    public void test() {
        TreeNode root = buildTree(new Integer[] {5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, 1});
        Assert.assertTrue(hasPathSum(root, 22));
    }

}
