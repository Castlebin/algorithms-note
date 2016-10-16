package ch15.se09;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class RandomAccessFileChannelTest {
    public static void main(String[] args) {
        try (
                FileChannel randomChannel = new RandomAccessFile(".gitignore.tmp", "rw").getChannel();
        ) {
            // 将Channel中的所有数据映射为ByteBuffer
            ByteBuffer buffer = randomChannel.map(FileChannel.MapMode.READ_ONLY, 0, randomChannel.size());
            // 把randomChannel的position指针移到最后面
            randomChannel.position(randomChannel.size());
            // 将buffer中的所有内容输出
            randomChannel.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
