package ch17.se03.noblock;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class NClient {
    private Selector selector;
    private static final int PORT = 30000;
    // 客户端的SocketChannel
    private SocketChannel socketChannel;

    public void init() throws IOException {
        selector = Selector.open();
        socketChannel = SocketChannel.open(new InetSocketAddress("0.0.0.0", PORT));
        // 设置该SocketChannel以非阻塞方式工作
        socketChannel.configureBlocking(false);
        // 将该SocketChannel注册到Selector上
        socketChannel.register(selector, SelectionKey.OP_READ);

        // 启动一个读取来自服务端数据的线程
        new ClientThread().start();

        // 创建键盘输入流
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            // 将内容输出到SocketChannel中
            socketChannel.write(StandardCharsets.UTF_8.encode(scanner.nextLine()));
        }
    }

    // 定义读取服务端数据的线程
    private class ClientThread extends Thread {
        @Override
        public void run() {
            try {
                while (selector.select() > 0) {
                    for (SelectionKey sk : selector.selectedKeys()) {
                        selector.selectedKeys().remove(sk);
                        if (sk.isReadable()) {
                            SocketChannel sc = (SocketChannel) sk.channel();
                            ByteBuffer buffer = ByteBuffer.allocate(1024);
                            StringBuilder sb = new StringBuilder();
                            while (sc.read(buffer) > 0) {
                                buffer.flip();
                                sb.append(StandardCharsets.UTF_8.decode(buffer));
                            }
                            System.out.println("读取到的内容：" + sb.toString());
                            // 为下一次读取做准备
                            sk.interestOps(SelectionKey.OP_READ);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new NClient().init();
    }
}
