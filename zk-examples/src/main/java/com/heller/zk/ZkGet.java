package com.heller.zk;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
  查看节点数据
 // 同步方式
 getData(String path, boolean b, Stat stat)
 // 异步方式
 getData(String path, boolean b，AsyncCallback.DataCallback callBack，
 Object ctx)

 */
public class ZkGet {

    ZooKeeper zooKeeper;
    String connectString = "127.0.0.1:2181";

    @Test
    public void get1() throws KeeperException, InterruptedException {
        if (zooKeeper.exists("/zk/get", false) == null) {
            zooKeeper.create("/zk/get", "ddddd".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
        Stat stat = new Stat();
        byte[] data = zooKeeper.getData("/zk/get", false, stat);
        System.out.println(new String(data));
        System.out.println(stat);
    }

    @Test
    public void get2() throws KeeperException, InterruptedException {
        zooKeeper.getData("/zk/get", false, new AsyncCallback.DataCallback() {
            @Override
            public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
                System.out.println(stat.getCtime());
                System.out.println(path);
                System.out.println(ctx);
                System.out.println(stat);
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
