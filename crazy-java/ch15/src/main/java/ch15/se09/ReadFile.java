package ch15.se09;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;

// 使用NIO时也可以像传统方式一段一段的读取数据内容
// 在文件很大时可以采取这种方式
public class ReadFile {
    public static void main(String[] args) {
        try (
                FileChannel inputChannel = new FileInputStream(".gitignore.tmp").getChannel();
        ) {
            Charset charset = StandardCharsets.UTF_8;
            CharsetDecoder decoder = charset.newDecoder();
            ByteBuffer buffer = ByteBuffer.allocate(8);
            while (inputChannel.read(buffer) != -1) {
                buffer.flip();
                System.out.print(decoder.decode(buffer));
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
