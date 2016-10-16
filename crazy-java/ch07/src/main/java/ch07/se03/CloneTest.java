package ch07.se03;

class Address {
    String detail;
    public Address(String detail) {
        this.detail = detail;
    }
}

class User implements Cloneable {
    int age;
    Address address;
    public User(int age, Address address) {
        this.age = age;
        this.address = address;
    }

    @Override
    public User clone() throws CloneNotSupportedException {
        return (User)super.clone();
    }
}

public class CloneTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        Address address = new Address("北京朝阳区");
        User u1 = new User(28, address);
        User u2 = u1.clone();

        // 可以清楚的看出默认的clone方法只能实现 “浅拷贝”
        System.out.println(u1 == u2);
        System.out.println(u1.address == u2.address);
    }
}
