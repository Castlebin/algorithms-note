package ch08;

import java.io.Serializable;

/**
    对于方法重载，编译器在编译期间，选择"相对合适"的方法版本进行调用，生成字节码，这是一种静态分配

 由于这个特点，只能选择"相对合适"的重载方法

 静态分配，以编译时声明的对象类型为准，不会看对象的运行时类型

 */
public class OverloadTest {
    public static void sayHello(Object arg) {
        System.out.println("hello Object");
    }
    public static void sayHello(int arg) {
        System.out.println("hello int");
    }
    public static void sayHello(long arg) {
        System.out.println("hello long");
    }
    public static void sayHello(Character arg) {
        System.out.println("hello Character");
    }
    public static void sayHello(char arg) {
        System.out.println("hello char");
    }
    public static void sayHello(char... arg) {
        System.out.println("hello char ...");
    }
    public static void sayHello(Serializable arg) {
        System.out.println("hello Serializable");
    }
    public static void main(String[] args) {
        sayHello('a');
    }
}
