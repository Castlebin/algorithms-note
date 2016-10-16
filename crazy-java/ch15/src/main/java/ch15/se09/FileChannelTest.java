package ch15.se09;

import java.io.*;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class FileChannelTest {
    public static void main(String[] args) throws FileNotFoundException {
        try (
                // 创建FileInputStream，以该文件输入流创建FileChannel
                FileChannel inputChannel = new FileInputStream(new File(".gitignore")).getChannel();
                // 同理，创建输出用的outputChannel
                FileChannel outputChannel = new FileOutputStream(new File(".gitignore")).getChannel();
        ) {
            // 将inputChannel里的全部数据映射为ByteBuffer
            MappedByteBuffer buffer = inputChannel.map(FileChannel.MapMode.READ_ONLY, 0, inputChannel.size());

            // 直接将buffer中的数据全部输出
            outputChannel.write(buffer);

            // 调用buffer的clear()方法，恢复limit, position指针的位置
            buffer.clear();

            // 字符解码器
            Charset charset = Charset.forName("utf-8");
            // 创建解码器(CharsetDecoder)对象
            CharsetDecoder decoder = charset.newDecoder();
            // 使用解码器将ByteBuffer转换为CharBuffer
            CharBuffer charBuffer = decoder.decode(buffer);
            // CharBuffer的toString()方法可以获取对应的字符串
            System.out.println(charBuffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
