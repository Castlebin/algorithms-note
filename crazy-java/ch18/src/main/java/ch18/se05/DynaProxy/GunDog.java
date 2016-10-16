package ch18.se05.DynaProxy;

public class GunDog implements Dog {
    @Override
    public void info() {
        System.out.println("我是一直猎狗");
    }

    @Override
    public void run() {
        System.out.println("我奔跑迅速");
    }
}
