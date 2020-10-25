package com.heller.zk;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * 简单的一个可以动态刷新的配置，利用了 zk 的监听机制
 * 
 * getData 只监听 指定路径节点的变化
 *
 * 而且利用了 连接时的 watcher 是可以一直重用的，不需要重新注册
 */
public class MyConfig {

    ZooKeeper zooKeeper;
    String connectString = "127.0.0.1:2181";

    // 本地的一些配置信息
    private String url;
    private String username;
    private String password;

    @Test
    public void testMyConfig() throws Exception {
        new MyConfig();
    }

    public MyConfig() throws Exception {
        init();
        initValue();

        Thread.sleep(500000);
    }

    private void initValue() throws KeeperException, InterruptedException {
        this.url = new String(zooKeeper.getData("/config/url", true, null));
        this.username = new String(zooKeeper.getData("/config/username", true, null));
        this.password = new String(zooKeeper.getData("/config/password", true, null));

        printConfigInfo();
    }

    public void init() throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        zooKeeper = new ZooKeeper(connectString, 5000, event -> {
            if (event.getType() == Watcher.Event.EventType.None) {
                // 连接创建成功
                if (event.getState() == Watcher.Event.KeeperState.SyncConnected) {
                    System.out.println("连接成功！");
                    countDownLatch.countDown();
                } else if (event.getState() == Watcher.Event.KeeperState.Disconnected) {
                    System.out.println("连接断开！");
                } else if (event.getState() == Watcher.Event.KeeperState.Expired) {
                    System.out.println("连接超时");
                } else if (event.getState() == Watcher.Event.KeeperState.AuthFailed) {
                    System.out.println("权限校验失败！");
                }
            } else if (event.getType() == Watcher.Event.EventType.NodeDataChanged) {
                try {
                    initValue();
                } catch (KeeperException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        // 连接默认是异步的，等待连接创建成功
        countDownLatch.await();
    }

    public void close() throws InterruptedException {
        zooKeeper.close();
    }

    public void printConfigInfo() {
        System.out.println("url=" + url + ", username=" + username + ", password=" + password);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
