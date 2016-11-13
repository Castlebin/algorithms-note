package list;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntUnaryOperator;

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

        Factorial factorial = new Factorial();
        IntUnaryOperator fact = factorial.fact;
        int[] arr = new int[] {1, 2, 3,4,5};
        for (int i : arr) {
            System.out.println(fact.applyAsInt(i));
        }
    }
}

class Factorial {
    IntUnaryOperator fact;

    public Factorial() {
        fact = i -> i == 0 ? 1 : i * fact.applyAsInt(i - 1);
    }
}
