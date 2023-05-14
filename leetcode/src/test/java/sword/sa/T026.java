package sword.sa;

import org.junit.Assert;
import org.junit.Test;

import common.TreeUtil;
import leetcode.base.TreeNode;

/**
 剑指 Offer 26. 树的子结构  (有点难啊)

 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
 B是A的子结构， 即 A中有出现和B相同的结构和节点值。
 */
public class T026 {

    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null) { // (约定空树不是任意一个树的子结构)
            return false;
        }
        return dfs(A, B)
                || isSubStructure(A.left, B)
                || isSubStructure(A.right, B);
    }

    boolean dfs(TreeNode a, TreeNode b) {
        if (b == null) {
            return true;
        }
        if (a == null) {
            return false;
        }
        return a.val == b.val
                && dfs(a.left, b.left)
                && dfs(a.right, b.right);
    }

    @Test
    public void test() {
        Assert.assertEquals(false, isSubStructure(
                TreeUtil.buildTree(new Integer[] {1, 2, 3}),
                TreeUtil.buildTree(new Integer[] {3, 1})
        ));
        Assert.assertEquals(true, isSubStructure(
                TreeUtil.buildTree(new Integer[] {3, 4, 5, 1, 2}),
                TreeUtil.buildTree(new Integer[] {4, 1})
        ));
    }

}
