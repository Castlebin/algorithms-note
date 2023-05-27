package sword.sa;

import leetcode.base.TreeNode;
import org.junit.Test;

/**
 剑指 Offer 68 - II. 二叉树的最近公共祖先
 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。

 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 */
public class T068 {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 返回节点存在情况
        if (root == null || p == root || q == root) {
            return root;
        }
        // 在左右子树寻找 p q 两个节点
        TreeNode leftResult = lowestCommonAncestor(root.left, p, q);
        TreeNode rightResult = lowestCommonAncestor(root.right, p, q);
        // 情况1：如果右子树找不到 p 或 q 即(right==null)，
        // 那么说明 p 和 q 都在左子树上，返回 left

        // 情况2：如果左子树找不到 p 或 q 即(right==null)，
        // 那么说明 p 和 q 都在右子树上，返回 right

        // 如果上述情况都不符合，说明 p 和 q 分别在左子树和右子树，
        // 那么最近公共节点为当前节点
        // 直接返回 root 即可
        if (leftResult != null && rightResult != null) {
            return root;
        }
        return leftResult != null ? leftResult : rightResult;
    }

    @Test
    public void test() {

    }

}
