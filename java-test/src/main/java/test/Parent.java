package test;

public class Parent {

    public Parent() {
        System.out.println("new Parent");
        init();
    }

    protected void init() {
        System.out.println("Parent.init");
    }

}
