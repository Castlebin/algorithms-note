package ch01.se01.se01_tcp_soa.consumer;

import ch01.se01.se01_tcp_soa.service.SayHelloService;
import ch01.se01.se01_tcp_soa.service.impl.SayHelloServiceImpl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

// 服务提供者的关键代码
public class Main {
    private static Map<String, Object> services = new HashMap<>();

    static {
        services.put(SayHelloService.class.getName(), new SayHelloServiceImpl());
    }

    public static void main(String[] args) throws NoSuchMethodException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        ServerSocket server = new ServerSocket(1234);
        while (true) {
            Socket socket = server.accept();
            // 读取消费者传过来的请求信息
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            String interfaceName = input.readUTF();// 接口名称
            String methodName = input.readUTF();// 方法名称
            Class<?>[] paramTypes = (Class<?>[])input.readObject();// 参数类型
            Object[] params = (Object[])input.readObject();// 参数对象

            // 执行调用
            Class serviceInterfaceClass = Class.forName(interfaceName);// 得到接口的Class
            Object service = services.get(interfaceName);// 得到服务实现类的对象
            Method method = serviceInterfaceClass.getMethod(methodName, paramTypes);
            Object result = method.invoke(service, params);
            System.out.println("rpc server result: " + result);

            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            output.writeObject(result);
        }
    }
}
