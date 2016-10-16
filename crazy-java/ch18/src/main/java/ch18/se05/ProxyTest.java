package ch18.se05;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface Person {
    void walk();
    void sayHello(String name);
}

class MyInvocationHandler implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("---正在执行的方法：" + method);
        if (args != null) {
            System.out.println("执行该方法传入的实参为：");
            for (Object arg : args) {
                System.out.println(arg);
            }
        } else {
            System.out.println("该方法没有参数");
        }

        return null;
    }
}

public class ProxyTest {
    public static void main(String[] args) {
        // 创建一个InvocationHandler对象
        InvocationHandler invocationHandler = new MyInvocationHandler();
        Person p = (Person) Proxy.newProxyInstance(Person.class.getClassLoader(),
                                                   new Class[] {Person.class},
                                                   invocationHandler);
        p.walk();
        p.sayHello("heller");
    }
}
