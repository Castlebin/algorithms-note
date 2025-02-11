package leetcode.N200_N299;

import org.junit.Test;
import org.junit.Assert;

/**
 * 236. 二叉树的最近公共祖先
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * 
 * 解题思路：
 * 1. 递归遍历二叉树，对于当前节点 root：
 *    - 如果 root 是 p 或 q，则 root 就是最近公共祖先
 *    - 否则递归搜索左右子树
 * 2. 递归返回值的含义：
 *    - 如果子树包含 p 或 q，则返回第一个找到的节点
 *    - 如果子树不包含 p 和 q，则返回 null
 * 3. 结果判断：
 *    - 如果左右子树都返回非空，说明 p 和 q 分别在左右子树中，当前节点就是最近公共祖先
 *    - 如果只有一个子树返回非空，则返回该非空节点
 */
public class T236 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 基本情况：空树或找到目标节点
        if (root == null) {
            return null;
        }
        // 如果当前节点是 p 或 q，直接返回当前节点
        if (root == p || root == q) {
            return root;
        }
        
        // 递归搜索左右子树
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        
        // 如果左右子树都找到了节点，说明当前节点就是最近公共祖先
        if (left != null && right != null) {
            return root;
        }
        
        // 返回非空的子树结果
        return left != null ? left : right;
    }

    // 二叉树节点定义
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    @Test
    public void test() {
        // 构建测试树：
        //       3
        //     /   \
        //    5     1
        //   / \   / \
        //  6   2 0   8
        //     / \
        //    7   4
        TreeNode root = new TreeNode(3);
        TreeNode node5 = new TreeNode(5);
        TreeNode node1 = new TreeNode(1);
        TreeNode node6 = new TreeNode(6);
        TreeNode node2 = new TreeNode(2);
        TreeNode node0 = new TreeNode(0);
        TreeNode node8 = new TreeNode(8);
        TreeNode node7 = new TreeNode(7);
        TreeNode node4 = new TreeNode(4);
        
        root.left = node5;
        root.right = node1;
        node5.left = node6;
        node5.right = node2;
        node1.left = node0;
        node1.right = node8;
        node2.left = node7;
        node2.right = node4;
        
        // 测试用例 1：节点在不同子树
        Assert.assertEquals(root, lowestCommonAncestor(root, node5, node1));  // 3是5和1的LCA
        
        // 测试用例 2：一个节点是另一个节点的祖先
        Assert.assertEquals(node5, lowestCommonAncestor(root, node5, node4)); // 5是5和4的LCA
        
        // 测试用例 3：节点在同一子树
        Assert.assertEquals(node5, lowestCommonAncestor(root, node6, node4)); // 5是6和4的LCA
        
        // 测试用例 4：节点是相邻的
        Assert.assertEquals(node2, lowestCommonAncestor(root, node7, node4)); // 2是7和4的LCA
        
        // 测试用例 5：根节点参与
        Assert.assertEquals(root, lowestCommonAncestor(root, root, node8));   // 3是3和8的LCA
    }
    
}
