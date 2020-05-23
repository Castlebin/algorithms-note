package ch07;

/**
 * 非主动使用类字段演示
 查看类加载情况

 -XX:+TraceClassLoading

 **/
public class NotInitialization {
    public static void main(String[] args) {
        System.out.println(SubClass.value);
    }
}
