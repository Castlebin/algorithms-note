package sword;

import common.TreeNode;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

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

    /**
     * 按题目要求，本题适合用层序遍历实现  （ TreeNode.buildBinaryTreeFromArray() 其实就是 反序列化 ）
     */
    public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            List<Integer> serialized = new ArrayList<>();
            serialize(root, serialized);

            StringBuffer sb = new StringBuffer("[");
            for (Integer val : serialized) {
                sb.append(val + ",");
            }
            if (serialized.size() > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.append("]");
            return sb.toString();
        }

        public List<Integer> serialize(TreeNode root, List<Integer> result) {
            if (root == null) {
                return result;
            }
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                TreeNode parent = queue.remove();
                if (parent != null) {
                    result.add(parent.val);
                    queue.add(parent.left);
                    queue.add(parent.right);
                } else {
                    result.add(null);
                }
            }
            // 处理一下末尾的null
            int i = result.size() - 1;
            for (; i >= 0; i--) {
                if (result.get(i) != null) {
                    break;
                }
                result.remove(i);
            }

            return result;
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data == null || data.length() == 0
                    || !data.startsWith("[") || !data.endsWith("]")
                    || data.length() == 2) {
                return null;
            }
            List<Integer> serialized = Arrays.stream(data.substring(1, data.length() - 1)
                    .split(",")).map(
                    s -> {
                        if ("null".equals(s)) {
                            return null;
                        } else {
                            return new Integer(s.trim());
                        }
                    }
            ).collect(Collectors.toList());
            return deserialize(serialized);
        }

        public TreeNode deserialize(List<Integer> serialized) {
            if (serialized == null || serialized.size() == 0) {
                return null;
            }
            TreeNode root = new TreeNode(serialized.get(0));
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            int index = 1;
            while (!queue.isEmpty() && index < serialized.size()) {
                TreeNode parent = queue.remove();
                Integer val = serialized.get(index++);
                if (val != null) {
                    parent.left = new TreeNode(val);
                    queue.add(parent.left);
                }
                if (index < serialized.size()) {
                    val = serialized.get(index++);
                    if (val != null) {
                        parent.right = new TreeNode(val);
                        queue.add(parent.right);
                    }
                }
            }

            return root;
        }

    }

    /**
     * 剑指 offer 上的序列化、反序列化方法（跟题目要求不一样）
     */
    public class BookExample {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            return serialize(root, new StringBuilder()).toString();
        }

        public StringBuilder serialize(TreeNode root, StringBuilder serialized) {
            if (root != null) {
                serialized.append(root.val + ",");
                serialize(root.left, serialized);
                serialize(root.right, serialized);
            } else {
                serialized.append("$,");
            }
            return serialized;
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data == null || data.length() == 0 || data.startsWith("$")) {
                return null;
            }
            return deserialize(data, new AtomicInteger());
        }

        public TreeNode deserialize(String data, AtomicInteger start) {
            int splitIndex = data.indexOf(",", start.get());
            if (splitIndex != -1) {
                String val = data.substring(start.get(), splitIndex);
                start.set(splitIndex + 1);
                if (!val.equals("$")) {
                    TreeNode root = new TreeNode(Integer.parseInt(val));
                    root.left = deserialize(data, start);
                    root.right = deserialize(data, start);
                    return root;
                }
            }
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

    @Test
    public void testBookExample() {
        BookExample codec = new BookExample();
        TreeNode tree = TreeNode.buildBinaryTreeFromArray(new Integer[]{1,2,3,null,null,4,5});
        tree = TreeNode.buildBinaryTreeFromArray(new Integer[]{1,2,3,4,null,5,6});

        String serialized = codec.serialize(tree);
        TreeNode deserialized = codec.deserialize(serialized);
        System.out.println(deserialized);
    }

}
