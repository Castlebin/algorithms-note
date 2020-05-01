package ds.ch03.exe;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class TreeTraversalsAgain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = Integer.parseInt(sc.nextLine());
        String[] headLine = sc.nextLine().split("\\s+");
        TreeNode root = new TreeNode(Integer.parseInt(headLine[1]));
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode cur = root;
        TreeNode prev = null;
        for (int i = 1; i < num * 2; i++) {
            String line = sc.nextLine();
            if (line.startsWith("Push")) {
                String[] newLine = line.split("\\s+");
                TreeNode newNode = new TreeNode(Integer.parseInt(newLine[1]));
                if (prev != null && prev.right == null) {
                    prev.right = newNode;
                } else if (cur.left == null) {
                    cur.left = newNode;
                }
                stack.push(newNode);
                cur = newNode;
            } else {
                prev = stack.pop();
            }
        }
        // 至此，树构造完毕
        // 后序遍历
        List<Integer> result = new ArrayList<>();
        postOrderTravel(root, result);
        StringBuilder sb = new StringBuilder(result.get(0)+"");
        for (int i = 1; i < result.size(); i++) {
            sb.append(" " + result.get(i));
        }
        System.out.println(sb.toString());
    }

    static void postOrderTravel(TreeNode root, List<Integer> result) {
        if (root != null) {
            postOrderTravel(root.left, result);
            postOrderTravel(root.right, result);
            result.add(root.data);
        }
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
