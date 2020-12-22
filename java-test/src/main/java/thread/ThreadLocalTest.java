package thread;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ThreadLocal、InheritableThreadLocal 和 阿里开源的 TransmittableThreadLocal 对比
 */
public class ThreadLocalTest {

    @Test
    public void testThreadLocal() {
        final ThreadLocal<String> mainThreadLocalStr = new ThreadLocal<>();
        mainThreadLocalStr.set(("Hello World!"));
        System.out.println(mainThreadLocalStr.get());
        Thread subThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(mainThreadLocalStr.get());
            }
        });
        subThread.start();
    }

    @Test
    public void testInheritableThreadLocal() {
        final ThreadLocal<String> mainThreadLocalStr = new InheritableThreadLocal<>();
        final ThreadLocal<String> mainThreadLocalStr2 = new InheritableThreadLocal<>();
        mainThreadLocalStr.set(("Hello World!"));
        mainThreadLocalStr2.set(("Hello World2!"));
        System.out.println(mainThreadLocalStr.get());
        Thread subThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(mainThreadLocalStr.get());
                    System.out.println(mainThreadLocalStr2.get());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        subThread.start();

        try {
            Thread.sleep(2000);
            // 子线程创建之后，父线程再改变 自己的 InheritableThreadLocal 的值，子线程内对应的 InheritableThreadLocal 不会变化
            mainThreadLocalStr2.set("nice day");
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(mainThreadLocalStr2.get());

    }

    /**
     * 测试一下在线程池中使用  InheritableThreadLocal
     */
    @Test
    public void testInheritableThreadLocal2() {
        ThreadLocal<String> threadLocal = new InheritableThreadLocal<>();

        ExecutorService threadPool = Executors.newFixedThreadPool(2);// 创建一个固定大小为2的线程池
        for (int i = 0; i < 5; i++) {
            threadLocal.set("初始化的值能继承吗？" + i);
            System.out.println("父线程的ThreadLocal值：" + threadLocal.get());
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("子线程到了");
                    System.out.println("=========子线程的ThreadLocal值：" + threadLocal.get());
                }
            });
        }
    }

    @Test
    public void testTransmittableThreadLocal() {
        final ThreadLocal<String> mainThreadLocalStr = new TransmittableThreadLocal<>();
        final ThreadLocal<String> mainThreadLocalStr2 = new TransmittableThreadLocal<>();
        mainThreadLocalStr.set(("Hello World!"));
        mainThreadLocalStr2.set(("Hello World2!"));
        System.out.println(mainThreadLocalStr.get());
        Thread subThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(mainThreadLocalStr.get());
                    System.out.println(mainThreadLocalStr2.get());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        subThread.start();

        try {
            Thread.sleep(2000);
            // 子线程创建之后，父线程再改变 自己的 TransmittableThreadLocal 的值，子线程内对应的 InheritableThreadLocal 不会变化
            mainThreadLocalStr2.set("nice day");
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(mainThreadLocalStr2.get());

    }

    /**
     * 测试一下在线程池中使用  TransmittableThreadLocal ，线程池也没有问题，正常继承了
     */
    @Test
    public void testTransmittableThreadLocal2() {
        ThreadLocal<String> threadLocal = new TransmittableThreadLocal<>();

        ExecutorService threadPool = TtlExecutors.getTtlExecutorService(Executors.newFixedThreadPool(2));// 创建一个固定大小为2的线程池
        for (int i = 0; i < 5; i++) {
            threadLocal.set("初始化的值能继承吗？" + i);
            System.out.println("父线程的ThreadLocal值：" + threadLocal.get());
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("子线程到了");
                    System.out.println("=========子线程的ThreadLocal值：" + threadLocal.get());
                }
            });
        }
    }

}
