package ch18.se05.DynaProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyDogInvocationHandler implements InvocationHandler {

    // 需要被代理的对象
    private Object target;

    public MyDogInvocationHandler(Object target) {
        this.target = target;
    }

    // 执行动态代理对象的所有方法时，都会被替换为执行invoke方法
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MyDogInterceptor interceptor = new MyDogInterceptor();

        interceptor.method1();

        // 执行被代理对象实际的方法
        Object result = method.invoke(target, args);

        interceptor.method2();

        return result;
    }
}
