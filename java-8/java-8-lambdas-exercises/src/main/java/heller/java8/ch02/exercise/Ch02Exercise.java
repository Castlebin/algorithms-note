package heller.java8.ch02.exercise;

import javax.swing.text.DateFormatter;
import java.text.SimpleDateFormat;

public class Ch02Exercise {

    /**
     * 2. ThreadLocal Lambda 表达式。Java 有一个ThreadLocal 类，作为容器保存了当前线程里
     * 局部变量的值。Java 8 为该类新加了一个工厂方法，接受一个Lambda 表达式，并产生
     * 一个新的ThreadLocal 对象，而不用使用继承，语法上更加简洁。
     * a. 在Javadoc 或集成开发环境（IDE）里找出该方法。
     * b. DateFormatter 类是非线程安全的。使用构造函数创建一个线程安全的DateFormatter
     * 对象，并输出日期，如“01-Jan-1970”。
     */
    public static final ThreadLocal<DateFormatter> FORMATTER
            = ThreadLocal.withInitial(() -> new DateFormatter(new SimpleDateFormat("yyyy-MM-dd")));

}
