package ch07.se02;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class SystemTest {
    public static void main(String[] args) throws IOException {
        // 获取系统的所有环境变量
        Map<String, String> env = System.getenv();
        for (String name : env.keySet()) {
            System.out.println(name + " ---> " + env.get(name));
        }

        // 获取指定的环境变量值
        System.out.println(env.get("JAVA_HOME"));

        // 获取所有的系统属性
        Properties props = System.getProperties();
        // 保存到文件中
        props.store(new FileOutputStream("props.log"), "System properties");
        // 获取特定的系统属性
        System.out.println(System.getProperty("os.name"));
    }
}
