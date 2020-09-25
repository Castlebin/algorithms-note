package sword;

import common.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 剑指 Offer 34. 二叉树中和为某一值的路径

 输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。

 从**树的根节点**开始往下一直到**叶节点**所经过的节点形成一条路径。

 (注意审题，本题路径的定义，必须是从根节点到叶节点！)
 */
public class S034 {

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> paths = new ArrayList<>();
        if (root != null) {
            List<Integer> path = new ArrayList<>();
            findPath(root, sum, 0, path, paths);
        }
        return paths;
    }

    private void findPath(TreeNode root, int sum, int currentSum,
                          List<Integer>path, List<List<Integer>> paths) {
        if (root != null) {
            currentSum += root.val;
            path.add(root.val);
            // 必须是到叶节点
            if (currentSum == sum && root.left == null && root.right == null) {
                paths.add(new ArrayList<>(Arrays.asList(path.toArray(new Integer[]{}))));
            }
            // 继续去左右子树递归寻找
            findPath(root.left, sum, currentSum, path, paths);
            findPath(root.right, sum, currentSum, path, paths);

            // 返回到上一级时，删除本路径上节点
            path.remove(path.size() - 1);
        }
    }

    @Test
    public void test() {
        TreeNode tree = TreeNode.buildBinaryTreeFromArray(new Integer[]{5,4,8,11,null,13,4,7,2, null, null, 5, 1});
        List<List<Integer>> lists = pathSum(tree, 22);
        System.out.println(lists.toString());
    }

}
