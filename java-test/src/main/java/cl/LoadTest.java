package cl;

public class LoadTest {

    public static void main(String[] args) throws Exception {
        new Thread(() -> {
            System.out.println("Hello World.");
            new Thread(() -> A.print()).run();
            new Thread(() -> A.printt()).run();
        }).start();
        new Thread(() -> {
            System.out.println("Hello World-2.");
            new Thread(() -> A.print()).run();
            new Thread(() -> A.printt()).run();
        }).start();
        new Thread(() -> {
            System.out.println("Hello World-3.");
            new Thread(() -> A.print()).run();
            new Thread(() -> A.printt()).run();
        }).start();
        new Thread(() -> {
            System.out.println("Hello World-4.");
            new Thread(() -> A.print()).run();
            new Thread(() -> A.printt()).run();
        }).start();
        System.out.println("xxxx");
        System.out.println(Class.forName("cl.A").toString());
    }

}
