package ds.ch03;

/**
 * 二叉树 的 节点定义
 *
 * 方便起见，把属性都定义为public了
 */
public class TreeNode {

    /**
     * 表示一下节点的data域
     */
    public Integer data;

    public TreeNode left;

    public TreeNode right;

    public TreeNode(Integer data) {
        this.data = data;
    }


    public int getHeight(TreeNode root) {
        if (root == null) {
            // 没有节点的树，高度定义为0
            return 0;
        }
        // 树的高度，等于它的子树的最大高度+1
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

}