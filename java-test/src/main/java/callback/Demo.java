package callback;

public class Demo {
    public static void main(String[] args) {
        Server server = new Server();

        Client client = new Client(server);

        // 客户端发送消息给服务端进行处理
        client.sendMsg("Server, Hello");
    }
}
