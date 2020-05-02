package ds.ch04.exe;

import java.util.*;

/**
 * 04-树6 Complete Binary Search Tree (30分)
 */
public class CompleteBinarySearchTree {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numCount = Integer.parseInt(sc.nextLine());
        String[] nums = sc.nextLine().split("\\s+");
        List<Integer> inputNumList = new ArrayList<>();
        for (int i = 0; i < numCount; i++) {
            inputNumList.add(Integer.parseInt(nums[i]));
        }
        Collections.sort(inputNumList);

        // 构建出一颗对应的完全二叉树
        TreeNode cbst = buildCompleteTree(numCount);
        List<Integer> targetNumList = new ArrayList<>();
        for (TreeNode node : cbst.nodes) {
            targetNumList.add(node.data);
        }
        Collections.sort(targetNumList);
        Map<Integer, Integer> map = new HashMap<>();
        int i = 0;
        for (Integer integer : targetNumList) {
            map.put(integer, i++);
        }
        StringBuilder sb = new StringBuilder();
        for (TreeNode node : cbst.nodes) {
            sb.append(inputNumList.get(map.get(node.data)) + " ");
        }
        System.out.println(sb.toString().trim());
    }

    private static TreeNode buildCompleteTree(int numCount) {
        int height = getHeight(numCount);
        int completeTreeNodeCount = (int) Math.pow(2, height) - 1;

        List<TreeNode> nodes = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add((completeTreeNodeCount + 1) / 2);
        TreeNode cbst = new TreeNode(queue.remove());
        nodes.add(cbst);
        cbst.height = height;
        int[] nums = new int[numCount];
        nums[0] = cbst.data;
        queue.add(cbst.data - (int)Math.pow(2, height - 2));
        queue.add(2 * cbst.data - (cbst.data - (int)Math.pow(2, height - 2)));
        int i = 1;
        while (!queue.isEmpty() && i < nums.length) {
            Integer data = queue.remove();
            nums[i++] = data;
            TreeNode node = new TreeNode(data);
            cbst = insert(node, cbst);
            nodes.add(node);
            int left = data - (int)Math.pow(2, node.height - 2);
            if (left > 0) {
                int right = 2 * data - left;
                queue.add(left);
                queue.add(right);
            }
        }

        cbst.nodes = nodes;
        return cbst;
    }

    private static int getHeight(int numCount) {
        int height = 0;
        int numMax = 0;
        while (numCount > numMax) {
            height++;
            numMax = (int)Math.pow(2, height) - 1;
        }
        return height;
    }

    public static TreeNode insert(TreeNode node, TreeNode bst) {
        if (bst == null) {
            return node;
        }
        if (node.data < bst.data) {
            bst.left = insert(node, bst.left);
            bst.left.height = bst.height - 1;
        } else if (node.data > bst.data) {
            bst.right = insert(node, bst.right);
            bst.right.height = bst.height - 1;
        }
        return bst;
    }

    static class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;
        int height = 1;

        List<TreeNode> nodes;

        public TreeNode(int data) {
            this.data = data;
        }
    }
}
