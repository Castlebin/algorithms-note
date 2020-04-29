package ds.ch03.exe;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;

/*
03-树2 List Leaves (25分)
        Given a tree, you are supposed to list all the leaves in the order of top down, and left to right.

        Input Specification:
        Each input file contains one test case. For each case, the first line gives a positive integer N (≤10) which is the total number of nodes in the tree -- and hence the nodes are numbered from 0 to N−1. Then N lines follow, each corresponds to a node, and gives the indices of the left and right children of the node. If the child does not exist, a "-" will be put at the position. Any pair of children are separated by a space.

        Output Specification:
        For each test case, print in one line all the leaves' indices in the order of top down, and left to right. There must be exactly one space between any adjacent numbers, and no extra space at the end of the line.

        Sample Input:
        8
        1 -
        - -
        0 -
        2 7
        - -
        - -
        5 -
        4 6

        Sample Output:
        4 1 5
*/
public class ListLeaves {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nodes = Integer.parseInt(sc.nextLine());
        int[] flags = new int[nodes];
        List<Node> nodeList = new ArrayList<>();
        String[][] lines = new String[nodes][];
        for (int i = 0; i < nodes; i++) {
            String[] children = sc.nextLine().split("\\s+");
            lines[i] = children;
            String left = children[0];
            if (!left.equals("-")) {
                flags[Integer.parseInt(left)] = 1;
            }
            String right = children[1];
            if (!right.equals("-")) {
                flags[Integer.parseInt(right)] = 1;
            }
            Node node = new Node();
            node.data = i;
            nodeList.add(node);
        }
        int root = findRoot(flags);
        Node rootNode = nodeList.get(root);
        for (int i = 0; i < lines.length; i++) {
            Node node = nodeList.get(i);
            String[] children = lines[i];
            String left = children[0];
            if (!left.equals("-")) {
                node.left = nodeList.get(Integer.parseInt(left));
            }
            String right = children[1];
            if (!right.equals("-")) {
                node.right = nodeList.get(Integer.parseInt(right));
            }
        }

        // 广度遍历
        Queue<Node> queue = new LinkedBlockingQueue<>();
        queue.add(rootNode);
        StringBuilder sb = new StringBuilder();
        while (queue.size() != 0) {
            Node node = queue.poll();
            if (node.left == null && node.right == null) {
                sb.append(" " + node.data);
            } else {
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }

        System.out.println(sb.toString().trim());
    }

    private static int findRoot(int[] flags) {
        for (int i = 0; i < flags.length; i++) {
            if (flags[i] != 1) {
                return i;
            }
        }
        return -1;
    }

    static class Node {
        int data;
        Node left;
        Node right;
    }

}
