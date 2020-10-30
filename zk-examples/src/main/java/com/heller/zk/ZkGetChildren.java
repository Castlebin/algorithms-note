package com.heller.zk;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
  查看子节点

 */
public class ZkGetChildren {

    ZooKeeper zooKeeper;
    String connectString = "127.0.0.1:2181";

    @Test
    public void getChildren1() throws KeeperException, InterruptedException {
        if (zooKeeper.exists("/zk/getChildren", false) == null) {
            zooKeeper.create("/zk/getChildren", "ddddd".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
        Stat stat = new Stat();
        List<String> children = zooKeeper.getChildren("/zk/getChildren", false);
        for (String child : children) {
            System.out.println(child);
        }
    }

    @Test
    public void getChildren2() throws KeeperException, InterruptedException {
        zooKeeper.getChildren("/zk/getChildren", false, new AsyncCallback.ChildrenCallback() {
            @Override
            public void processResult(int rc, String path, Object ctx, List<String> children) {
                System.out.println(path);
                System.out.println(ctx);
                for (String child : children) {
                    System.out.println(child);
                }
            }
        }, "I'm the context Object");
    }

    @Before
    public void before() throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        zooKeeper = new ZooKeeper(connectString, 5000, event -> {
            // 连接创建成功
            if (event.getState() == Watcher.Event.KeeperState.SyncConnected) {
                countDownLatch.countDown();
            }
        });
        // 连接默认是异步的，等待连接创建成功
        countDownLatch.await();
        System.out.println("zk 连接创建成功了！sessionId=" + zooKeeper.getSessionId());
    }

    @After
    public void after() throws InterruptedException {
        zooKeeper.close();
    }

}
