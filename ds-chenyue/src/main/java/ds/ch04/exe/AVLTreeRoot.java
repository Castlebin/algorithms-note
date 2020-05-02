package ds.ch04.exe;

import java.util.Scanner;

public class AVLTreeRoot {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nodeCount = Integer.parseInt(sc.nextLine());
        String[] nums = sc.nextLine().split("\\s+");

        AVL avl = null;
        for (int i = 0; i < nodeCount; i++) {
            avl = insert(Integer.parseInt(nums[i]), avl);
        }
        System.out.println(avl.data);
    }

    public static AVL insert(int element, AVL avl) {
        if (avl == null) {
            avl = new AVL(element);
        }
        if (element < avl.data) {
            avl.left = insert(element, avl.left);
            // 如果需要左旋（里面判断是需要 左单旋 还是 左-右双旋 ）
            if (getHeight(avl.left) - getHeight(avl.right) == 2) {
                if (element < avl.left.data) {// 说明插入的是左边的左边，即LL插入
                    avl = singleLeftRotation(avl);
                } else {
                    avl = doubleLeftRightRotation(avl);
                }
            }
        } else if (element > avl.data) {
            avl.right = insert(element, avl.right);
            // 如果需要右旋（里面判断是需要 右单旋 还是 右-左双旋）
            if (getHeight(avl.right) - getHeight(avl.left) == 2) {
                if (element > avl.right.data) {// 说明插入的是右边的右边，即RR插入
                    avl = singleRightRotation(avl);
                } else {
                    avl = doubleRightLeftRotation(avl);
                }
            }
        }

        return avl;
    }

    private static AVL singleLeftRotation(AVL a) {
        AVL b = a.left;
        a.left = b.right;
        b.right = a;
        return b;
    }

    private static AVL singleRightRotation(AVL a) {
        AVL b = a.right;
        a.right = b.left;
        b.left = a;
        return b;
    }

    private static AVL doubleLeftRightRotation(AVL a) {
        a.left = singleRightRotation(a.left);
        return singleLeftRotation(a);
    }

    private static AVL doubleRightLeftRotation(AVL a) {
        a.right = singleLeftRotation(a.right);
        return singleRightRotation(a);
    }

    private static int getHeight(AVL avl) {
        if (avl == null) {
            return 0;
        }
        return max(getHeight(avl.left), getHeight(avl.right)) + 1;
    }

    public static int max(int a, int b) {
        return a >= b? a : b;
    }

    static class AVL {
        int data;
        AVL left;
        AVL right;

        public AVL(int data) {
            this.data = data;
        }
    }
}
