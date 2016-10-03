package polymorphism;

class Dog {

    public static void bark() {
        System.out.println("Dog bark.");
    }

}

class Bolt extends Dog {

    public static void bark() {
        System.out.println("Bolt bark.");
    }

}

class GunDog extends Dog {

    public static void bark() {
        System.out.println("GunDog bark.");
    }

}

class Test {
    public static void main(String[] args) {
        Dog dog = new GunDog();
        dog.bark();// 静态方法不符合多态规则！此处打印出Dog bark.
        Dog dog2 = new Bolt();
        dog2.bark();// 静态方法不符合多态规则！此处打印出Dog bark.
    }
}
