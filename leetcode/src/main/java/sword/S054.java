package sword;

import org.junit.Test;
import common.TreeNode;

import java.util.Stack;

/**
 剑指 Offer 54. 二叉搜索树的第k大节点

 给定一棵二叉搜索树，请找出其中第k大的节点。

 中序遍历的使用（左->中->右  变成  右->中->左 即可）
*/
public class S054 {

    public int kthLargest(TreeNode root, int k) {
        TreeNode cur = root;
        Stack<TreeNode> stack = new Stack<>();
        int count = 0;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.right;
            }
            if (!stack.isEmpty()) {
                cur = stack.pop();
                count++;
                if (count == k) {
                    return cur.val;
                }
                cur = cur.left;
            }
        }
        return -1;
    }

    @Test
    public void test() {

    }

}
