package ch00;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class TestSet {

    @Test
    public void putDuplicateElement() {
        Set<String> set = new HashSet<>();
        set.add("1");
        set.add("1");
        set.add("1");
        set.add("1");// 不会成功添加进去，返回值为false
    }

}
