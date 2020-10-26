package com.heller.zk;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

public class ZkWatcherExist {

    ZooKeeper zooKeeper;
    String connectString = "127.0.0.1:2181";

    @Test
    public void watcherExist() throws KeeperException, InterruptedException {
        Stat exists = zooKeeper.exists("/exist/node1", false);
        System.out.println(exists.getVersion());
        System.out.println(exists.getDataLength());
    }

    @Test
    public void watcherExist1() throws KeeperException, InterruptedException {
        // 第二个参数 true，表示使用 创建连接时的 watcher
        zooKeeper.exists("/exist/node1", true);
        Thread.sleep(20000);
    }

    /**
     * 除了 connection 的监听器外
     * 其他这些监听器，都是一次性的
     */
    @Test
    public void watcherExist2() throws KeeperException, InterruptedException {
        // 这些事件 watcher 都是一次性的
        zooKeeper.exists("/exist/node1", new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("watcherExist2");
                System.out.println("event type: " + event.getType());
                System.out.println("event path: " + event.getPath());
                System.out.println("event state: " + event.getState());
            }
        });
        Thread.sleep(20000);
    }

    /**
     * 让 watcher 能够一直监听
     */
    @Test
    public void watcherExist3() throws KeeperException, InterruptedException {
        zooKeeper.exists("/exist/node1", new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("watcherExist3");
                System.out.println("event type: " + event.getType());
                System.out.println("event path: " + event.getPath());
                System.out.println("event state: " + event.getState());

                // 再次注册，通过这种方式，可以使得 watch 永远监听，是不是很简单？
                try {
                    zooKeeper.exists("/exist/node1", this);
                } catch (KeeperException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread.sleep(20000);
    }

    @Before
    public void before() throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        zooKeeper = new ZooKeeper(connectString, 5000, event -> {
            // 连接创建成功
            if (event.getType() == Watcher.Event.EventType.None) {
                if (event.getState() == Watcher.Event.KeeperState.SyncConnected) {
                    countDownLatch.countDown();
                }
            }
        });
        // 连接默认是异步的，等待连接创建成功
        countDownLatch.await();
    }

    @After
    public void after() throws InterruptedException {
        zooKeeper.close();
    }

}
