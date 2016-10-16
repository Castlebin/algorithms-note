package ch18.se05.DynaProxy;

public class MyDogInterceptor {

    // 第一个拦截器方法
    public void method1() {
        System.out.println("===模拟第一个通用拦截器方法===");
    }

    // 第二个拦截器方法
    public void method2() {
        System.out.println("===模拟第二个通用拦截器方法===");
    }

}
