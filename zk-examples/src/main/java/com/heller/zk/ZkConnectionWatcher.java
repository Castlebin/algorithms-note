package com.heller.zk;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

/**
 * 测试 watcher 监听 zk 连接事件
 */
public class ZkConnectionWatcher implements Watcher {

    static CountDownLatch countDownLatch = new CountDownLatch(1);
    static ZooKeeper zooKeeper;

    public static void main(String[] args) {
        try {
            zooKeeper = new ZooKeeper("127.0.0.1:2181", 20000, new ZkConnectionWatcher());
            // 等待 zk 连接创建成功
            countDownLatch.await();
            System.out.println(zooKeeper.getSessionId());
            Thread.sleep(500000);
            zooKeeper.close();
            System.out.println("结束");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println(watchedEvent.getType());
        System.out.println(watchedEvent.getState());

        if (watchedEvent.getType() == Event.EventType.None) {
            if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
                // 说明 zk 连接创建成功了
                System.out.println("zk 连接创建成功");
                countDownLatch.countDown();
            } else if (watchedEvent.getState() == Event.KeeperState.Closed) {
                System.out.println("断开连接");
            } else if (watchedEvent.getState() == Event.KeeperState.Expired) {
                System.out.println("会话超时");
            }
        }
    }
}
