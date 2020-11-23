package com.insightfullogic.java8.answers.chapter2;

import java.text.SimpleDateFormat;
import java.util.function.Predicate;

import javax.swing.text.DateFormatter;

public class Question2 {

    public final static ThreadLocal<DateFormatter> formatter
            = ThreadLocal.withInitial(() -> new DateFormatter(new SimpleDateFormat("dd-MMM-yyyy")));

/*      // 无法做出类型推断
    public static void main(String[] args) {
        check(x -> x > 5);
    }*/

    public static boolean check(Predicate<Integer> predicate) {
        return true;
    }

    public static boolean check(IntPred predicate) {
        return true;
    }

}


class Question3 {

}

interface IntPred {
    boolean test(Integer value);
}
