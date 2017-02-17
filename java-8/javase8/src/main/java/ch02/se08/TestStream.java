package ch02.se08;

import org.junit.Test;

import java.util.Optional;
import java.util.stream.Stream;

public class TestStream {

    Integer[] digits = {
            3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5, 8, 9, 7, 9, 3, 2, 3, 8, 4, 6, 2, 6, 4, 3, 3, 8, 3, 2, 7, 9, 5, 0, 2, 8, 8, 4, 1, 9, 7, 1, 6, 9, 3, 9, 9, 3, 7, 5, 1, 0, 5, 8, 2, 0, 9, 7, 4, 9, 4, 4, 5, 9, 2, 3, 0, 7, 8, 1, 6, 4, 0, 6, 2, 8, 6};

    @Test
    public void testReduce() {
        Stream<Integer> values = Stream.of(digits);
        Optional<Integer> reduce = values.reduce((x, y) -> x + y);
        reduce.ifPresent(System.out::println);
    }

    @Test
    public void testReduce2() {
        Stream<Integer> values = Stream.of(digits);
        Optional<Integer> reduce = values.reduce(Integer::sum);
        reduce.ifPresent(System.out::println);
    }

    @Test
    public void testReduce3() {
        Stream<Integer> values = Stream.of(digits);
        Integer reduce = values.reduce(0, Integer::sum);// 提供了一个初始值的reduce方法,不必返回Optional类型的值了
        System.out.println(reduce);
    }
}
