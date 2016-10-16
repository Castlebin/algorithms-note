package ch18.se05.DynaProxy;

public class Test {
    public static void main(String[] args) {
        Dog gundog = new GunDog();
        // 为目标对象创建一个动态代理
        Dog dog = (Dog)MyProxyFactory.getProxy(gundog);
        dog.info();
        dog.run();
    }
}
