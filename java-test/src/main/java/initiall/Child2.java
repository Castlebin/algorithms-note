package initiall;

public class Child2 extends Parent2 {

    /**
     * 必须调用父类的构造器！否则编译都不通过
     *
     * 由此可见，java内部新建一个子类对象时，必然要调用父类的构造器，必然会建立同时新建一个对应的父类对象
     *//*
    public Child2() {

    }
    */

    public Child2() {
        super("parent2");
    }

    public Child2(String name) {
        super("parent2");
    }

}
