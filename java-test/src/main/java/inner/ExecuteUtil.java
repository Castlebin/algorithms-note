package inner;

import org.junit.Test;

/**
 * 3. 匿名内部类
 *
 * 同局部内部类一样的，匿名内部类也是不能有访问修饰符和 static 修饰符的。
 */
public class ExecuteUtil {

    public static void execute(Command command) {
        command.execute();
    }

    @Test
    public void testAnonymousInnerClass() {
        ExecuteUtil.execute(new Command() {
            @Override
            public void execute() {
                System.out.println("Hello World!");
            }
        });
    }

}
