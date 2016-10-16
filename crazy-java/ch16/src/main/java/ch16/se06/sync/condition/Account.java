package ch16.se06.sync.condition;

import java.math.BigDecimal;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

// 实现一种特殊的需求，要求账户种一存入钱立马取出，不允许取钱操作或存钱操作连续执行，只允许交错执行
public class Account {
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    private String accountNo;
    private BigDecimal balance = BigDecimal.ZERO;

    public Account(String accountNo) {
        this.accountNo = accountNo;
    }

    // 标识账户中是否有存款的旗标（此处必须使用volatile关键字，保证线程之前的可见性！）
    private volatile boolean flag = false;

    public BigDecimal getBalance() {
        return balance;
    }

    // 取款方法
    public void draw() {
        lock.lock();
        try {
            // 如果flag为false，表示还没有人存钱进去，取款方法等待
            if (!flag) {
                condition.await();
            } else {
                // 可以执行取钱操作
                System.out.println(Thread.currentThread().getName() + " 取钱： " + balance + ", 账户余额为：0");
                balance = BigDecimal.ZERO;
                // 没钱啦
                flag = false;
                // 唤醒其他线程
                condition.signalAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    // 存钱方法
    public void deposit(BigDecimal amount) {
        lock.lock();
        try {
            // 如果flag为true，表示账户里还有钱，存钱方法等待
            if (flag) {
                condition.await();
            } else {
                // 可以执行存款操作
                balance = balance.add(amount);
                System.out.println(Thread.currentThread().getName() + " 存入： " + amount + ", 账户余额为：" + balance);
                // 有钱啦
                flag = true;
                // 唤醒取款线程
                condition.signalAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
