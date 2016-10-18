package ch17.se03.simple_aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;

public class SimpleAIOClient {
    private static final int PORT = 30001;

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        // 用于读取数据的ByteBuffer
        ByteBuffer buff = ByteBuffer.allocate(1024);
        try (
                // 创建AsynchronousSocketChannel对象
                AsynchronousSocketChannel socketChannel = AsynchronousSocketChannel.open()
        ) {
            // 连接服务器
            socketChannel.connect(new InetSocketAddress("127.0.0.1", PORT)).get();
            buff.clear();
            // 从socketChannel中读取获得的数据
            socketChannel.read(buff).get();
            buff.flip();
            System.out.println("获取到的数据：" + StandardCharsets.UTF_8.decode(buff).toString());
        }
    }
}
