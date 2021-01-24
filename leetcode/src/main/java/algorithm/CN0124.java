package algorithm;

import common.TreeNode;

/**
 * 124. 二叉树中的最大路径和
 * 路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。
 *
 * 该路径 至少包含一个 节点，且不一定经过根节点。
 *
 * 路径和 是路径中各节点值的总和。
 *
 * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
 */
public class CN0124 {

    int maxPathSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxGain(root);
        return maxPathSum;
    }

    /**
     * 思路：
     * 对于一个节点，以这个节点作为公共父节点的路径，其最大路径和为：
     * 它左边子儿子能贡献的最大路径和 +
     * 它右边子儿子能贡献的最大路径和 +
     * 节点本身的值
     *
     * 它自己能往上贡献的最大值，为 左右两个子节点能贡献的最大值+它本身的值
     */
    public int maxGain(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftGain = Math.max(0, maxGain(node.left));
        int rightGain = Math.max(0, maxGain(node.right));


        // 在这里，看是不是要更新下最大的路径和
        int thisMaxPathSum = leftGain + rightGain + node.val;
        if (thisMaxPathSum > maxPathSum) {
            maxPathSum = thisMaxPathSum;
        }


        return Math.max(leftGain, rightGain) + node.val;
    }

}
