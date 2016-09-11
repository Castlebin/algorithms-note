package ch00;

import org.junit.Test;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class TestMap {

    @Test
    public void putDuplicateKey() {
        Map<String, String> map = new HashMap<>();
        map.put("1", "1");
        map.put("1", "2");
        map.put("1", "3");// 会覆盖掉前面的k-v, 不会抛出异常
    }

    @Test
    public void putDuplicateKeyHashtable() {
        Map<String, String> map = new Hashtable<>();
        map.put("1", "1");
        map.put("1", "2");
        map.put("1", "3");// 会覆盖掉前面的k-v, 不会抛出异常
    }

}
