package map;

import java.util.HashMap;
import java.util.Map;

public class HashMapTest {

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();

        map.put("政治", 5);
        map.put("生物", 7);
        map.put("历史", 4);
        map.put("数学", 2);
        map.put("化学", 8);
        map.put("语文", 1);
        map.put("英语", 3);
        map.put("地理", 6);

        System.out.println(map);
    }

}
