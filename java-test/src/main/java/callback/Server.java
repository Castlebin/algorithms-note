package callback;

import java.util.Date;

/**
 * 回调模式
 *
 * 模拟服务端
 */
public class Server {

    public void processClientMsg(String msg, CSCallBack callBack) {
        System.out.println(new Date() + "服务端：接收到客户端发送的消息：" + msg);

        // 模拟服务器端处理任务延时
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String status = "200";
        System.out.println(new Date() + "服务端：处理客户端请求完成，返回 " + status);

        // 回调
        callBack.callback(status);
    }

}
