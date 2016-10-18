package ch17.se03.simple_aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class SimpleAIOServer {
    private static final int PORT = 30001;

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        try (
                // 创建AsynchronousServerSocketChannel对象
                AsynchronousServerSocketChannel serverSocketChannel = AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(PORT))
        ) {
            while (true) {
                Future<AsynchronousSocketChannel> future = serverSocketChannel.accept();
                // 获取连接完成后建立的AsynchronousSocketChannel
                AsynchronousSocketChannel socketChannel = future.get();
                // 执行输出
                socketChannel.write(ByteBuffer.wrap("欢迎来到AIO的世界！".getBytes("UTF-8"))).get();
            }
        }
    }
}
