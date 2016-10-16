package ch16.se06.sync.Account;

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
        for (int i = 0; i < 20; i++) {
            account.draw();
        }
    }

}
