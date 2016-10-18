package ch17.se03.aio;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;

public class AcceptHandler implements CompletionHandler<AsynchronousSocketChannel, Object> {
    private AsynchronousServerSocketChannel serverSocketChannel;
    // 定义一个ByteBuffer用于读取数据
    ByteBuffer buff = ByteBuffer.allocate(1024);

    public AcceptHandler(AsynchronousServerSocketChannel serverSocketChannel) {
        this.serverSocketChannel = serverSocketChannel;
    }

    // 当实际IO操作完成时触发该方法
    @Override
    public void completed(AsynchronousSocketChannel sc, Object attachment) {
        // 记录新连接进来的Channel
        AIOServer.channelList.add(sc);
        serverSocketChannel.accept(null, this);
        sc.read(buff, null, new CompletionHandler<Integer, Object>() {
            @Override
            public void completed(Integer result, Object attachment) {
                buff.flip();
                for (AsynchronousSocketChannel c : AIOServer.channelList) {
                    String content = StandardCharsets.UTF_8.decode(buff).toString();
                    try {
                        c.write(ByteBuffer.wrap(content.getBytes("utf-8"))).get();
                    } catch (InterruptedException | ExecutionException | UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    buff.clear();
                    // 读取下一次数据
                    sc.read(buff, null, this);
                }
            }

            @Override
            public void failed(Throwable exc, Object attachment) {
                System.out.println("读取数据失败：" + exc);
                // 从该Channel中读取数据失败，就将该Channel从list中删除
                AIOServer.channelList.remove(sc);
            }
        });
    }

    @Override
    public void failed(Throwable exc, Object attachment) {
        System.out.println("连接失败：" + exc);
    }

}
