package com.heller.zk;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
    同样的，更新节点数据时，也可以使用 同步 和 异步 两种方式
     // 同步方式
     setData(String path, byte[] data, int version)
     // 异步方式
     setData(String path, byte[] data, int version，AsyncCallback.StatCallback callBack， Object ctx)

 */
public class ZkSet {

    ZooKeeper zooKeeper;
    String connectString = "127.0.0.1:2181";

    @Test
    public void set1() throws KeeperException, InterruptedException {
        if (zooKeeper.exists("/zk/set", false) == null) {
            zooKeeper.create("/zk/set", "ddddd".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
        zooKeeper.setData("/zk/set", "nideeee".getBytes(), -1);
    }

    @Test
    public void set2() throws KeeperException, InterruptedException {
        if (zooKeeper.exists("/zk/set", false) == null) {
            zooKeeper.create("/zk/set", "ddddd".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
        zooKeeper.setData("/zk/set", "fdfdfdfd".getBytes(), -1, new AsyncCallback.StatCallback() {
            @Override
            public void processResult(int rc, String path, Object ctx, Stat stat) {
                System.out.println(stat.getCtime());
                System.out.println(path);
                System.out.println(ctx);
                System.out.println(stat);
            }
        }, "a Context Object");
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
