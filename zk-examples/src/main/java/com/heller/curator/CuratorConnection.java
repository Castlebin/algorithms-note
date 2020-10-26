package com.heller.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * 使用 curator 与 zk 建立连接
 */
public class CuratorConnection {
    /**
     * session重连策略
     * // 仅重连一次，间隔3秒
     * RetryPolicy retryPolicy = new RetryOneTime(3000);
     * // 每3秒重连一次，重连3次
     * RetryPolicy retryPolicy = new RetryNTimes(3,3000);
     * // 每3秒重连一次，总等待时间超过10秒后停止重连
     * RetryPolicy retryPolicy=new RetryUntilElapsed(10000,3000);
     **/

    static RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
    static String connectString = "127.0.0.1:2181";

    public static void main(String[] args) {
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(connectString)
                .sessionTimeoutMs(5000)
                .retryPolicy(retryPolicy)
                .namespace("create")
                .build();
        client.start();
        System.out.println(client.isStarted());
        client.close();
    }
}
