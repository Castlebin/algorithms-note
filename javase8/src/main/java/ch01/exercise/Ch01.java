package ch01.exercise;

import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.BiFunction;

public class Ch01 {

    // NO.1 是的

    // NO.2
    @Test
    public void listSubDirectory() {
        String dir = "/home/heller";
        File file = new File(dir);
        File[] suDirs = file.listFiles(f -> f.isDirectory());
        System.out.println(Arrays.toString(suDirs));
        suDirs = file.listFiles(File::isDirectory);
        System.out.println(Arrays.toString(suDirs));
    }
    // NO.3
    @Test
    public void listSomeExtFile() {
        String dir = "/home/heller/Documents";
        File file = new File(dir);
        File[] pdfFiles = file.listFiles((dir1, name) -> name.toLowerCase().endsWith(".pdf"));
        System.out.println(Arrays.toString(pdfFiles));
    }

    @Test
    public void testFunction() {
        BiFunction<String, String, Integer> comparator =
                (first, second) -> Integer.compare(first.length(), second.length());
        BiFunction<String, String, Integer> comparator2 = String::compareTo;

        Comparator<String> stringComparator = (String x, String y) -> x.compareTo(y);
        Comparator<String> stringComparator2 = String::compareTo;// 更简便的写法(method reference: 方法引用)

        // Lambda表达式可以很方便的使用，被自由的转化为所需要的函数式表达式
        //（String::compareTo这个Lambda表达式轻松的赋值给了不同的函数式接口）
        // 事实上，函数式接口转换是我们在Java中使用lambda表达式能做的唯一一件事情
        System.out.println(comparator2);
        System.out.println(stringComparator2);
    }

}
