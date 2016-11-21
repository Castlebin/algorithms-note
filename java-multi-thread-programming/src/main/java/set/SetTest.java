package set;

import java.util.Set;
import java.util.TreeSet;

public class SetTest {
    public static void main(String[] args) {
        Set<Integer> set = new TreeSet<>();
        set.add(3);
        set.add(2);
        set.add(1);
        set.add(6);

        System.out.println(set.toString());
    }
}
