package callback;

import java.util.Date;

/**
 * 回调模式
 *
 * 模拟回调客户端
 */
public class Client implements CSCallBack {
    private Server server;

    public Client(Server server) {
        this.server = server;
    }

    @Override
    public void callback(String status) {
        System.out.println(new Date() + "客户端：服务端回调，处理状态为：" + status);
    }

    public void sendMsg(String msg) {
        System.out.println(new Date() + "客户端：发送消息：" + msg);
        new Thread(() -> server.processClientMsg(msg, this)).start();
        System.out.println(new Date() + "客户端：异步发送消息成功！");
    }

}
