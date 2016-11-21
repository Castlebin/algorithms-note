package ch16.se06.sync.wait;

// 取款线程
public class DrawThread extends Thread {

    // 要操作的账户
    private Account account;

    public DrawThread(String threadName, Account account) {
        super(threadName);
        this.account = account;
    }

    @Override
    public void run() {
        int i = 0;
        for (; i < 5; i++) {
            account.draw();
        }
        System.out.println("存款：" + i);
    }

}
