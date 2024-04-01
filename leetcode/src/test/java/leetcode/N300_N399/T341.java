package leetcode.N300_N399;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import common.NestedInteger;
import common.NestedIntegerUtil;

/**
 * 341. 扁平化嵌套列表迭代器
 */
class NestedIterator implements Iterator<Integer> {
    List<Integer> data;
    Iterator<Integer> iterator;

    public NestedIterator(List<NestedInteger> nestedList) {
        data = new ArrayList<>();
        for (NestedInteger n : nestedList) {
            traverse(n, data);
        }
        this.iterator = data.iterator();
    }

    private void traverse(NestedInteger nested, List<Integer> data) {
        if (nested.isInteger()) {
            data.add(nested.getInteger());
        } else {
            for (NestedInteger d : nested.getList()) {
                traverse(d, data);
            }
        }
    }

    @Override
    public Integer next() {
        return iterator.next();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }
}

public class T341 {
    @Test
    public void test() {
        List<NestedInteger> nestedList = NestedIntegerUtil.buildNestedIntegerList(new Object[] {
                new int[] {1, 1},
                2,
                new int[] {1, 1}
        });
        // nestedList.forEach(t -> System.out.println(t.toString()));

        NestedIterator iterator = new NestedIterator(nestedList);
        iterator.forEachRemaining(t -> System.out.println(t.toString()));
    }
}
