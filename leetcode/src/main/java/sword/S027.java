package sword;

import org.junit.Test;
import sword.common.TreeNode;

/**
 面试题27. 二叉树的镜像
 请完成一个函数，输入一个二叉树，该函数输出它的镜像。
 */
public class S027 {

    public TreeNode mirrorTree(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return root;
        }

        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        mirrorTree(root.left);
        mirrorTree(root.right);
        return root;
    }

    @Test
    public void test() {

    }

}
