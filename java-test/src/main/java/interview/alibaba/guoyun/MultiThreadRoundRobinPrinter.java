package interview.alibaba.guoyun;

/**
 * 2. 编写一个程序，开启3个线程，
 * 这3个线程的ID分别为A、B、C，3个线程交替打印1-100的整数，要求输出结果有序,
 * 样例Sample:
         Thread1: 1
         Thread2: 2
         Thread3: 3
         Thread1: 4
         Thread2: 5
         Thread3: 6
         ....
         Thread3: 99
         Thread1: 100
 *
 * @author Lgy
 * @desc 多个线程打印
 * @date 2020-12-19
 */
public class MultiThreadRoundRobinPrinter {
    private volatile int num =1;
    private volatile int threadIndex = 1;
    public void print(int thread){
        synchronized (this){
            while (threadIndex != thread ){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+":"+num);
            num++;
            threadIndex = thread%3 + 1;
            this.notifyAll();
        }
    }

    public static void main(String[] args) {
        final MultiThreadRoundRobinPrinter printer = new MultiThreadRoundRobinPrinter();

        for(int i=1;i < 4;i++){
            final int thread = i;
            final int count = i==1?34:33;
            new Thread(new Runnable() {
                public void run() {
                    for(int j=0;j<count;j++){
                        printer.print(thread);
                    }
                }
            },"Thread"+i).start();
        }

    }
}