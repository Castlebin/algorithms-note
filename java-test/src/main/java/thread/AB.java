package thread;

public class AB {

    public static void main(String[] args) {
        AB ab = new AB();
        Thread aThread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    ab.printA();
                }
            }
        };
        Thread bThread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    ab.printB();
                }
            }
        };
        aThread.start();
        bThread.start();
    }

    private boolean flag = true; // printA

    public synchronized void printA() {
        try {
            while (!flag) {
                wait();
            }
            System.out.println("A");
            flag = false;
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void printB() {
        try {
            while (flag) {
                wait();
            }
            System.out.println("B");
            flag = true;
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
