package ch17.se03.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AIOServer {
    private static final int PORT = 30002;
    static List<AsynchronousSocketChannel> channelList = new ArrayList<>();

    public void startListen() throws IOException, InterruptedException {
        // 创建一个线程池
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        // 以指定的线程池创建一个AsynchronousChannelGroup
        AsynchronousChannelGroup channelGroup = AsynchronousChannelGroup.withThreadPool(executorService);
        AsynchronousServerSocketChannel serverChannel = AsynchronousServerSocketChannel.open(channelGroup)
                .bind(new InetSocketAddress(PORT));
        // 使用CompletionHandler来处理来自客户端的请求
        serverChannel.accept(null, new AcceptHandler(serverChannel));
        Thread.sleep(50000);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        AIOServer server = new AIOServer();
        server.startListen();
    }
}
