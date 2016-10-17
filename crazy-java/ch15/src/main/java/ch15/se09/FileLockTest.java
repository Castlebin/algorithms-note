package ch15.se09;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class FileLockTest {
    public static void main(String[] args) {
        try (
                // 使用FileOutputStream获取FileChannel
                FileChannel outputChannel = new FileOutputStream(".gitignore.tmp").getChannel();
        ) {
            // 使用非阻塞的方式(tryLock)获取文件锁
            FileLock fileLock = outputChannel.tryLock();
            // 休眠10s。因为获取了文件锁，所以在这10s中其他线程是不能修改该文件的
            Thread.sleep(10000);
            // 释放文件锁
            fileLock.release();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
