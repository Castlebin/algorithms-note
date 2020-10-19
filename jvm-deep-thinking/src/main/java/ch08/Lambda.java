package ch08;

/**
 * Lambda 表达式 实现原理
 */
public class Lambda {
    public static void PrintString(String s, Print<String> print) {
        print.print(s);
    }
    // 编译后会生成一个静态内部类和一个名字为下面 lambda$main$0 的静态方法，所以，如果类中有同名方法，会编译出错
/*
    private static void lambda$main$0(String s) {
    }*/

    public static void main(String[] args) {
        PrintString("test", (x) -> System.out.println(x));
    }
}
