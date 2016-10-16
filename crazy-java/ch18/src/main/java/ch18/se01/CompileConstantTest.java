package ch18.se01;

class MyTest {
    static {
        System.out.println("静态初始化块…");
    }

    // 编译时就可以确定下来的常量，俗称“宏变量”
    static final String compileConstant = "疯狂Java讲义";

    // 非 “宏变量”
    static final String constant = System.currentTimeMillis() + "";
}

public class CompileConstantTest {
    public static void main(String[] args) {
        // 访问“宏变量”，JVM不会去执行类初始化
        System.out.println(MyTest.compileConstant);

        System.out.println("--------------------");

        // 非 “宏变量”， 将执行类初始化
        System.out.println(MyTest.constant);
    }
}
