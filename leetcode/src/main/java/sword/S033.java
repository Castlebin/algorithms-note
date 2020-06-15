package sword;

import org.junit.Test;

/**
 面试题33. 二叉搜索树的后序遍历序列
 输入一个整数数组，判断该数组是不是某 **二叉搜索树** 的后序遍历结果。
 如果是则返回 true，否则返回 false。
 假设输入的数组的任意两个数字都互不相同。
 */
public class S033 {

    public boolean verifyPostorder(int[] postorder) {
        if (postorder == null) {
            return false;
        }
        return verifyPostorder(postorder, 0, postorder.length - 1);
    }

    private boolean verifyPostorder(int[] postorder, int start, int end) {
        if (start >= end) {
            return true;
        }
        // 后序遍历的最后一个节点，是根节点
        int parent = postorder[end];
        // 由于是一棵二叉搜索树，所以，可以根据根节点，将树分成左右两棵子树
        int leftEnd = start;
        for (; leftEnd < end && postorder[leftEnd] <= parent; leftEnd++) {}

        // 会有两种特殊情况 1. leftEnd == start 说明没有左子树   2. leftEnd == end 说明没有右子树
        // 左子树中的元素，必须全部小于等于父节点
        if (leftEnd > start) {
            for (int index = start; index <= leftEnd - 1; index++) {
                if (postorder[index] > parent) {
                    return false;
                }
            }
        }
        // 右子树中的元素，必须全部大于等于父节点
        if (leftEnd < end) {
            for (int index = leftEnd; index <= end - 1; index++) {
                if (postorder[index] < parent) {
                    return false;
                }
            }
        }

        return verifyPostorder(postorder, start, leftEnd - 1)
                && verifyPostorder(postorder, leftEnd, end - 1);
    }

    @Test
    public void test() {

    }

}
