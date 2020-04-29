package ds.ch03.exe;

import java.util.*;

public class IsomorphismTree {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TreeDesc tree1 = buildTreeDesc(sc);
        TreeDesc tree2 = buildTreeDesc(sc);

        boolean iso = checkIso(tree1, tree2);
        System.out.println(iso?"Yes": "No");
    }

    private static boolean checkIso(TreeDesc tree1, TreeDesc tree2) {
        if (tree1.nodes.size() != tree2.nodes.size()) {
            return false;
        }
        Map<String, TreeNode> nodeMap1 = new HashMap<>();
        for (TreeNode node : tree1.nodes) {
            nodeMap1.put(node.data, node);
        }
        for (TreeNode node : tree2.nodes) {
            TreeNode comp = nodeMap1.get(node.data);
            if (comp == null) {
                return false;
            }
            if (!compare(node, comp)) {
                return false;
            }
        }

        return true;
    }

    private static boolean compare(TreeNode node, TreeNode comp) {
        if (node.left.equals(comp.left) && node.right.equals(comp.right)) {
            return true;
        }
        if (node.left.equals(comp.right) && node.right.equals(comp.left)) {
            return true;
        }
        return false;
    }

    private static TreeDesc buildTreeDesc(Scanner sc) {
        TreeDesc tree = new TreeDesc();
        int size = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < size; i++) {
            String[] tn = sc.nextLine().split("\\s+");
            TreeNode node = new TreeNode(tn[0], tn[1], tn[2]);
            tree.nodes.add(node);
        }

        for (TreeNode node : tree.nodes) {
            if (!node.left.equals("-")) {
                node.left = tree.nodes.get(Integer.parseInt(node.left)).data;
            }
            if (!node.right.equals("-")) {
                node.right = tree.nodes.get(Integer.parseInt(node.right)).data;
            }
        }

        return tree;
    }

    public static class TreeDesc {
        List<TreeNode> nodes = new ArrayList<>();
    }

    public static class TreeNode {
        String data;
        String left;
        String right;

        public TreeNode(String data, String left, String right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

}
