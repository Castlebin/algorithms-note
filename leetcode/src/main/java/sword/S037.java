package sword;

import common.TreeNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 剑指 Offer 37. 序列化二叉树
 请实现两个函数，分别用来序列化和反序列化二叉树。

 示例：
 你可以将以下二叉树：

        1
       / \
      2   3
     / \
    4   5

 序列化为 "[1,2,3,null,null,4,5]"
 */
public class S037 {

    public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            return null;
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            return null;
        }
    }

    @Test
    public void test() {
        Codec codec = new Codec();
        TreeNode tree = TreeNode.buildBinaryTreeFromArray(new Integer[]{1,2,3,null,null,4,5});
    //    tree = TreeNode.buildBinaryTreeFromArray(new Integer[]{1,2,3,4,null,5,6});


        String serialized = codec.serialize(tree);
        TreeNode deserialized = codec.deserialize(serialized);
        System.out.println(deserialized);
    }

}
