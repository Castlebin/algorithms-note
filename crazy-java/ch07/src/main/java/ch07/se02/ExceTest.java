package ch07.se02;

import java.io.IOException;

public class ExceTest {
    public static void main(String[] args) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        // 启动一个单独的进程来运行操作系统命令
        runtime.exec("notepad.exe");
    }
}
