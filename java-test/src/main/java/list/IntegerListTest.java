package list;

import java.util.ArrayList;
import java.util.List;

public class IntegerListTest {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(8);
        list.add(3);
        list.add(4);
        list.add(2);
        System.out.println(list);

        list.remove(3);
        System.out.println(list);

        list.remove(new Integer(3));
        System.out.println(list);
    }
}
