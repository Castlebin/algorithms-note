package ch08;

/**
 * 方法静态分派演示

    静态分配，以编译时声明的对象类型为准，不会看对象的运行时类型

 输出为：
 hello,guy!
 hello,guy!

 */
public class StaticDispatch {

    public void sayHello(Human guy) {
        System.out.println("hello,guy!");
    }

    public void sayHello(Man guy) {
        System.out.println("hello,gentleman!");
    }

    public void sayHello(Woman guy) {
        System.out.println("hello,lady!");
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();

        StaticDispatch sr = new StaticDispatch();
        sr.sayHello(man);
        sr.sayHello(woman);
    }
}

abstract class Human {
}

class Man extends Human {
}

class Woman extends Human {
}

