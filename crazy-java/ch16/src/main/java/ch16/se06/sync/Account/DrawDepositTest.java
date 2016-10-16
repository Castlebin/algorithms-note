package ch16.se06.sync.Account;

import java.math.BigDecimal;

public class DrawDepositTest {
    public static void main(String[] args) {
        Account account = new Account("00001");

        DepositThread dp1 = new DepositThread("存款者-1", account, new BigDecimal(500));
        DepositThread dp2 = new DepositThread("存款者-2", account, new BigDecimal(800));
        DepositThread dp3 = new DepositThread("存款者-3", account, new BigDecimal(700));
        DrawThread dt1 = new DrawThread("取款者-1", account);
        DrawThread dt2 = new DrawThread("取款者-2", account);
        DrawThread dt3 = new DrawThread("取款者-3", account);

        dp1.start();
        dp2.start();
        dp3.start();
        dt1.start();
        dt2.start();
        dt3.start();
    }

}
