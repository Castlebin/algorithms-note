package interview.alibaba.guoyun;

import java.util.ArrayList;

/**
 * 3. 遍历一个二叉树，打印出该路径中每个节点数字的和与给定目标值一致的有效路径。
 * @author Lgy
 * @desc
 * @date 2020-12-21
 */
public class PathSum {
    /**
     * 有效路径：从根节点到叶节点的路径。
     *    给定一个二叉树 :
     *      1
     *     / \
     *    2   4
     *   / \
     *  2   3
     *目标值= 5时返回结果：
     *      1 2 2
     *      1 4
     * @param root
     * @param sum
     * @return
     */
    public ArrayList<ArrayList<Integer>> pathSum(TreeNode root, int sum) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();
        paths(root, sum, list,res);
        return res;
    }
    private void paths(TreeNode root, int sum, ArrayList<Integer> list,ArrayList<ArrayList<Integer>> res){
        if(root == null){
            return;
        }
        if(root.left == null && root.right == null && sum - root.val == 0){
            list.add(root.val);
            res.add(new ArrayList<>(list));
            list.remove(list.size() - 1);
            return;
        }
        list.add(root.val);
        paths(root.left, sum - root.val, list,res);
        paths(root.right, sum - root.val, list,res);
        list.remove(list.size() - 1);
    }

    public static void main(String[] args) {
        PathSum pathSum =new PathSum();
        TreeNode root = new TreeNode(1);
        root.left=new TreeNode(2);
        root.right=new TreeNode(4);
        root.left.left=new TreeNode(2);
        root.left.right=new TreeNode(3);
        ArrayList<ArrayList<Integer>> list = pathSum.pathSum(root, 5);
        for (ArrayList<Integer> res : list) {
            System.out.println(res);
        }
    }
    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
