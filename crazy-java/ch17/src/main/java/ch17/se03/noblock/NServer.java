package ch17.se03.noblock;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;

// 使用非阻塞的NIO实现的聊天室服务端
public class NServer {
    // 用于注册所有Channel的Selector实例
    private Selector selector;

    private static final int PORT = 30000;

    public void init() throws IOException {
        selector = Selector.open();

        // 新建一个ServerSocketChannel实例 (相当于传统网络编程中的ServerSocket)
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 绑定端口
        serverSocketChannel.bind(new InetSocketAddress("0.0.0.0", PORT));
        // 设置ServerSocketChannel实例以非阻塞模式工作
        serverSocketChannel.configureBlocking(false);
        // 将ServerSocketChannel注册到Selector上
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (selector.select() > 0) {
            // 依次处理selector上每个已选择的SelectionKey
            for (SelectionKey selectionKey : selector.selectedKeys()) {
                // 从selector上的已选择的Key集合中删除正在处理的SelectionKey
                selector.selectedKeys().remove(selectionKey);
                // 如果SelectionKey对应的Channel上有来自客户端的连接请求
                if (selectionKey.isAcceptable()) {
                    // 接受连接，创建服务器端的SocketChannel（相当于传统网络编程中的Socket）
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    // 设置为采用非阻塞模式
                    socketChannel.configureBlocking(false);
                    // 将该SocketChannel也注册到Selector上
                    socketChannel.register(selector, SelectionKey.OP_READ);
                    // 将SelectionKey对应的Channel设置成准备接受其他请求
                    selectionKey.interestOps(SelectionKey.OP_ACCEPT);
                }
                // 如果SelectionKey对应的Channel上有数据需要读取
                if (selectionKey.isReadable()) {
                    // 获取该SelectionKey对应的Channel，从Channel中读取数据
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    // buffer
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    StringBuilder sb = new StringBuilder();
                    try {
                        while (socketChannel.read(buffer) > 0) {
                            buffer.flip();
                            sb.append(StandardCharsets.UTF_8.decode(buffer));
                        }
                        System.out.println("读取的数据：" + sb.toString());
                        // 将SelectionKey对应的Channel设置成准备下一次读取
                        selectionKey.interestOps(SelectionKey.OP_READ);
                    } catch (IOException e) {
                        // 如果捕获到该SelectionKey对应的Channel抛出了异常，
                        // 表示对应的client出了问题，所以从Selector中取消SelectionKey的注册
                        selectionKey.cancel();
                        if (selectionKey.channel() != null) {
                            selectionKey.channel().close();
                        }
                    }
                    // 如果内容不为空，表示有聊天内容
                    if (sb.toString().length() > 0) {
                        // 遍历该Selector上注册的所有SelectionKey
                        for (SelectionKey key : selector.keys()) {
                            Channel targetChannel = key.channel();
                            if (targetChannel instanceof SocketChannel) {
                                ((SocketChannel) targetChannel).write(StandardCharsets.UTF_8.encode(sb.toString()));
                            }
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new NServer().init();
    }
}
