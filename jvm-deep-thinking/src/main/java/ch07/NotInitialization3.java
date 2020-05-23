package ch07;

/**
 * 非主动使用类字段演示 3
 查看类加载情况

 -XX:+TraceClassLoading

 **/
/**
 * 非主动使用类字段演示 3
 **/
public class NotInitialization3 {
    public static void main(String[] args) {
        System.out.println(ConstClass.HELLOWORLD);
    }
}