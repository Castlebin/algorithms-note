package interview;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

public class N210127 {

    /**
      小米  2021.01.27 MIUI

      输入： 二叉树
      返回： 二叉树每层从右到左的第二个节点的值的和

      例子：
               1
           2      3
       4    5   6

      返回： 2 + 5 = 7
     */

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public int sumTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int sum = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode nextLevelRightestNode = root;
        int count = 0;
        while (!queue.isEmpty()) {
            TreeNode cur = queue.peek();

            if (cur == nextLevelRightestNode) {
                count = 0;

            //    System.out.println();

                // 换行了！ 必须找到下一行的最右边节点
                for (TreeNode node : queue) {
                    if (node.right != null) {
                        nextLevelRightestNode = node.right;
                        break;
                    } else if (node.left != null) {
                        nextLevelRightestNode = node.left;
                        break;
                    }
                }
            }
        //    System.out.print(cur.val);
            queue.remove();
            count++;
            if (count == 2) {
                sum += cur.val;
            }

            if (cur.right != null) {
                queue.add(cur.right);
            }
            if (cur.left != null) {
                queue.add(cur.left);
            }
        }
        return sum;
    }

    @Test
    public void test() {
        TreeNode root = new TreeNode(1);;
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);
        System.out.println(sumTree(root));
    }

}
