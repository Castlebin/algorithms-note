package ch18.se05.DynaProxy;

import java.lang.reflect.Proxy;

public class MyProxyFactory {

    // 为指定的target对象生成动态代理对象
    public static Object getProxy(Object target) {
        MyDogInvocationHandler handler = new MyDogInvocationHandler(target);
        // 创建并返回一个动态代理对象
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                                      target.getClass().getInterfaces(),
                                      handler);
    }
}
