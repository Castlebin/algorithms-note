package initiall;

public class Child extends Parent {
    public Child() {
        System.out.println("Child无参初始化");
    }

    public Child(String name) {
        System.out.println("Child有参初始化，name=" + name);
    }

}
