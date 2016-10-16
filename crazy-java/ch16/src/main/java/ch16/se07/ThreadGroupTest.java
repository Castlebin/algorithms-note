package ch16.se07;

class MyThread extends Thread {
    public MyThread() {}
    public MyThread(String name) {
        super(name);
    }
    public MyThread(ThreadGroup group, String name) {
        super(group, name);
    }
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }
}

public class ThreadGroupTest {
    public static void main(String[] args) {
        ThreadGroup mainThreadGroup = Thread.currentThread().getThreadGroup();
        System.out.println("主线程组的名字：" + mainThreadGroup.getName());
        System.out.println("是否为后台线程组：" + mainThreadGroup.isDaemon());
        new MyThread("主线程组的线程").start();

        ThreadGroup tg = new ThreadGroup("新线程组");
        tg.setDaemon(true);
        System.out.println("tg线程组是否为后台线程组：" + tg.isDaemon());
        Thread t1 = new MyThread(tg, "tg线程组的线程甲");
        t1.start();
        System.out.println("t1是否为后台线程："+t1.isDaemon());
        new MyThread(tg, "tg线程组的线程乙").start();
    }
}
