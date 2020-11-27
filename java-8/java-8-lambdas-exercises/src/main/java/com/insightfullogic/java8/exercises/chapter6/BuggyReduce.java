package com.insightfullogic.java8.exercises.chapter6;

import java.util.List;

public class BuggyReduce {

    public static int multiplyThrough(List<Integer> linkedListOfNumbers) {
        return linkedListOfNumbers.stream()
                                  .reduce(5, (acc, x) -> x * acc);
    }

    public static int multiplyThrough2(List<Integer> linkedListOfNumbers) {
        return linkedListOfNumbers.parallelStream()
                .reduce(1, (acc, x) -> acc * x) * 5;
    }

}
