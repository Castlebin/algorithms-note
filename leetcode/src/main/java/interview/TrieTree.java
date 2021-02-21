package interview;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 TrieTree 字典树的一个简单实现

 TrieTree 的根节点，不包含值

 实现往一颗 TrieTree 中添加单词、查找单词
 */
public class TrieTree {
    Character val;
    // 记录到当前位置，是否是一个单词，防止只是前缀匹配的情况
    boolean isWord;
    Map<Character, TrieTree> children;

    public void insertWord(String word) {
        if (word == null || word.length() == 0) {
            return;
        }
        TrieTree parent = this;
        TrieTree child = null;
        for(char cha : word.toCharArray()) {
            if (parent.children == null) {
                parent.children = new HashMap<>();
            }
            Map<Character, TrieTree> children = parent.children;
            // 如果当前字符在树里，就继续往下迭代下一个字符
            if (children.containsKey(cha)) {
                child = children.get(cha);
            } else {
                // 当前字符不在，需要添加进树里
                child = new TrieTree();
                child.val = cha;
                children.put(cha, child);
            }
            parent = child;
        }
        child.isWord = true;
    }

    public boolean findWord(String word) {
        if (word == null || word.length() == 0) {
            throw new IllegalArgumentException("要查找的单词不能为空");
        }
        Map<Character, TrieTree> children = this.children;
        TrieTree child = null;
        for(char cha : word.toCharArray()) {
            // 如果当前字符在树里，就继续往下迭代下一个字符
            if (children != null && children.containsKey(cha)) {
                child = children.get(cha);
                children = child.children;
            } else {
                return false;
            }
        }
        return child.isWord;
    }

    @Test
    public void testTrieTree() {
        TrieTree trieTree = new TrieTree();
        trieTree.insertWord("word");
        trieTree.insertWord("wordname");
        Assert.assertTrue(trieTree.findWord("word"));
        Assert.assertFalse(trieTree.findWord("wor"));
        Assert.assertFalse(trieTree.findWord("word1"));
    }
}
