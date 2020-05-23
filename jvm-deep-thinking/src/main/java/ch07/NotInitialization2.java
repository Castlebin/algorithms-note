package ch07;

/**
 * 非主动使用类字段演示 2
 查看类加载情况

 -XX:+TraceClassLoading

 **/
public class NotInitialization2 {
    public static void main(String[] args) {
        SuperClass[] sca = new SuperClass[10];
    }
}
