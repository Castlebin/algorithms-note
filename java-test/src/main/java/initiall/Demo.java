package initiall;

/**
 * new创建一个子类对象时，都会调用父类的构造方法，创建一个父类对象
 */
public class Demo {
    public static void main(String[] args) {
        Child c1 = new Child();

        Child c2 = new Child("heller");

        System.out.println();
    }
}
