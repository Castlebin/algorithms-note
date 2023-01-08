package ds.ch04.exe;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 04-树4 是否同一棵二叉搜索树 (25分)

 */
public class SameTree {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String firstLine = sc.nextLine();
            if (firstLine.equals("0")) {
                return;
            }
            String[] meta = firstLine.split("\\s+");
            int checkCount = Integer.parseInt(meta[1]);

            TreeNode bst = buildTree(sc);
            for (int i = 0; i < checkCount; i++) {
                String line = sc.nextLine();
                String[] nums = line.split("\\s+");
                boolean same = checkSameTree(nums, bst);
                System.out.println(same? "Yes":"No");
            }
        }
    }

    private static boolean checkSameTree(String[] nums, TreeNode bst) {
        Set<String> traveled = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (!checkSame(nums[i], traveled, bst)) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkSame(String num, Set<String> traveled, TreeNode bst) {
        if (bst == null) {
            return false;
        }
        int data = Integer.parseInt(num);
        if (data == bst.data) {
            traveled.add(num);
        } else { // 看看路径上的节点，是否都已经访问过了
            if (traveled.contains(Integer.toString(bst.data))) {
                if (data < bst.data) {
                    return checkSame(num, traveled, bst.left);
                } else {
                    return checkSame(num, traveled, bst.right);
                }
            } else {
                return false;
            }
        }
        return true;
    }

    private static TreeNode buildTree(Scanner sc) {
        String line = sc.nextLine();
        String[] nums = line.split("\\s+");
        TreeNode bst = null;
        for (int i = 0; i < nums.length; i++) {
            bst = insert(Integer.parseInt(nums[i]), bst);
        }
        return bst;
    }

    private static TreeNode insert(int element, TreeNode bst) {
        if (bst == null) {
            return new TreeNode(element);
        }
        if (element < bst.data) {
            bst.left = insert(element, bst.left);
        } else if (element > bst.data) {
            bst.right = insert(element, bst.right);
        }
        return bst;
    }

    static class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;

        public TreeNode(int data) {
            this.data = data;
        }
    }

}
