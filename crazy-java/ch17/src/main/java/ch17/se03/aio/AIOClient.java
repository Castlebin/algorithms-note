package ch17.se03.aio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AIOClient {
    private static final int PORT = 30002;
    AsynchronousSocketChannel clientChannel;

    JFrame mainWin = new JFrame("多人聊天室");
    JTextArea jta = new JTextArea(16, 48);
    JTextField jtf = new JTextField(40);
    JButton sendBn = new JButton("发送");

    public void init() {
        mainWin.setLayout(new BorderLayout());
        jta.setEnabled(true);
        mainWin.add(new JScrollPane(jta), BorderLayout.CENTER);
        JPanel jp = new JPanel();
        jp.add(jtf);
        jp.add(sendBn);

        // 为发送按钮绑定事件监听器
        Action sendAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String content = jtf.getText();
                if (content.trim().length() > 0) {
                    try {
                        // 将content写入Channel中
                        clientChannel.write(ByteBuffer.wrap(content.trim().getBytes("utf-8"))).get();
                    } catch (InterruptedException | ExecutionException | UnsupportedEncodingException e1) {
                        e1.printStackTrace();
                    }
                }
                // 清空输入框
                jtf.setText("");
            }
        };
        sendBn.addActionListener(sendAction);
        // 将Enter键和send关联
        jtf.getInputMap().put(KeyStroke.getKeyStroke('\n'), "send");
        // 将send和sendAction关联
        jtf.getActionMap().put("send", sendAction);
        mainWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWin.add(jp, BorderLayout.SOUTH);
        mainWin.pack();
        mainWin.setVisible(true);
    }

    public void connect() throws IOException, ExecutionException, InterruptedException {
        ByteBuffer buff = ByteBuffer.allocate(1024);
        ExecutorService executorService = Executors.newFixedThreadPool(80);
        AsynchronousChannelGroup channelGroup = AsynchronousChannelGroup.withThreadPool(executorService);
        clientChannel = AsynchronousSocketChannel.open(channelGroup);
        clientChannel.connect(new InetSocketAddress("127.0.0.1", PORT)).get();
        jta.append("---与服务器连接成功---\n");
        buff.clear();
        clientChannel.read(buff, null, new CompletionHandler<Integer, Object>() {
            @Override
            public void completed(Integer result, Object attachment) {
                buff.flip();
                String content = StandardCharsets.UTF_8.decode(buff).toString();
                // 从服务端读取数据
                jta.append("某人说：" + content + "\n");
                buff.clear();
                clientChannel.read(buff, null, this);
            }

            @Override
            public void failed(Throwable exc, Object attachment) {
                System.out.println("读取数据失败：" + exc);
            }
        });
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {
        AIOClient client = new AIOClient();
        client.init();
        client.connect();
    }
}
