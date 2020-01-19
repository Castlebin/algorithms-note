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

}
