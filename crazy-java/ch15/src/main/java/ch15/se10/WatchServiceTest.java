package ch15.se10;

import java.io.IOException;
import java.nio.file.*;

public class WatchServiceTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        // 获取文件系统的WatchService对象
        WatchService watchService = FileSystems.getDefault().newWatchService();
        // 为路径注册监听，监听 新建、删除、修改 事件
        Paths.get("/").register(watchService, StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY);
        while (true) {
            // 获取变化事件
            WatchKey watchKey = watchService.take();
            for (WatchEvent<?> event : watchKey.pollEvents()) {
                System.out.println(event.context() + " 文件 发生了 " + event.kind() + " 事件!");
            }

            // 重设WatchKey
            boolean valid = watchKey.reset();
            // 如果重设失败，退出监听
            if (!valid) {
                break;
            }
        }
    }
}
