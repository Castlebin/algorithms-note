package ch01.se01.se01_tcp_soa.consumer;

import ch01.se01.se01_tcp_soa.service.SayHelloService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

// 消费者的关键代码
public class Main {
    public static void main(String[] args) throws NoSuchMethodException, IOException, ClassNotFoundException {
        // 接口名称
        String interfaceName = SayHelloService.class.getName();
        // 需要远程执行的方法
        Method method = SayHelloService.class.getMethod("sayHello", String.class);
        // 调用方法时传入的参数
        Object[] params = {"xiaoming"};

        Socket socket = new Socket("127.0.0.1", 1234);
        // 将方法名称和参数传到远端
        ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
        output.writeUTF(interfaceName);// 接口名称
        output.writeUTF(method.getName());// 方法名称
        output.writeObject(method.getParameterTypes());
        output.writeObject(params);

        // 从远端读取结果
        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
        Object result = input.readObject();
        System.out.println(result);
    }
}
