package com.heller.zk;

import org.apache.zookeeper.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

public class ZkCreate {

    ZooKeeper zooKeeper;
    String connectString = "127.0.0.1:2181";

    /**
     * 测试 创建节点 （同步）
     */
    @Test(expected = KeeperException.NodeExistsException.class)
    public void create1() throws KeeperException, InterruptedException {
        if (zooKeeper.exists("/create", false) != null) {
            // 如果节点已经存在，先删除它
            zooKeeper.delete("/create", -1);
            System.out.println("delete exist node ");
        }
        zooKeeper.create("/create", "create".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        // 再次创建，出现异常，也说明创建成功了
        zooKeeper.create("/create", "create".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    /**
     * 测试 创建节点 （异步）
     */
    @Test
    public void create2() throws KeeperException, InterruptedException {
        if (zooKeeper.exists("/create", false) != null) {
            // 如果节点已经存在，先删除它
            zooKeeper.delete("/create", -1);
        }
        zooKeeper.create("/create", "create".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT,
                new AsyncCallback.StringCallback() {
                    @Override
                    public void processResult(int rc, String path, Object ctx, String name) {
                        // 0 代表节点创建成功
                        System.out.println(rc);
                        System.out.println(path);
                        System.out.println(name);
                        // 创建节点时，传入的 context 上下文参数
                        System.out.println(ctx);
                    }
                }, "I'm the context object!");
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
