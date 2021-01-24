package algorithm;

import common.TreeNode;
import org.junit.Test;

import java.util.Stack;

/**
 * 99. 恢复二叉搜索树
 * 给你二叉搜索树的根节点 root ，该树中的两个节点被错误地交换。请在不改变其结构的情况下，恢复这棵树。
 *
 * 进阶：使用 O(n) 空间复杂度的解法很容易实现。你能想出一个只使用常数空间的解决方案吗？
 */
public class CN0099 {

    public void recoverTree(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode firstNode = null;
        TreeNode secondNode = null;
        TreeNode prev = new TreeNode(Integer.MIN_VALUE);
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            if (!stack.isEmpty()) {
                cur = stack.pop();

                // 树的中序遍历， 加上这段代码而已。注意看两个节点的赋值算法
                if (prev.val > cur.val) {
                    if (firstNode == null) {
                        // 第一个节点找到了，这个时候，它后面的节点是有可能为第二个要找的节点的
                        firstNode = prev;
                        secondNode = cur;
                    } else {// 找到了间隔的第二个节点，说明可以退出算法了
                        secondNode = cur;
                        break;
                    }
                }


                prev = cur;
                cur = cur.right;
            }
        }
        if (firstNode != null && secondNode != null) {
            int tmp = firstNode.val;
            firstNode.val = secondNode.val;
            secondNode.val = tmp;
        }
    }

    @Test
    public void test() {
        TreeNode root = TreeNode.buildBinaryTreeFromArray(new Integer[]{3, 1, 4, null, null, 2});
        recoverTree(root);
    }

}
