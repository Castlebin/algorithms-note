package ch15.se10;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.util.Date;

public class AttribureViewTest {
    public static void main(String[] args) throws IOException {
        // 获取要操作的文件
        Path testPath = Paths.get("poem.txt");
        // 获取访问文件基本属性的BasicFileAttributeView
        BasicFileAttributeView basicView = Files.getFileAttributeView(testPath, BasicFileAttributeView.class);
        // 获取文件的基本属性
        BasicFileAttributes basicAttributes = basicView.readAttributes();
        System.out.println("创建时间：" + new Date(basicAttributes.creationTime().toMillis()));
        System.out.println("最后访问时间：" + new Date(basicAttributes.lastAccessTime().toMillis()));
        System.out.println("最后修改时间：" + new Date(basicAttributes.lastModifiedTime().toMillis()));
        System.out.println("文件大小：" + basicAttributes.size());

        // 获取访问DOS属性的DosAttributeView
        DosFileAttributeView dosView = Files.getFileAttributeView(testPath, DosFileAttributeView.class);
        // 设置文件 只读、隐藏 属性
        dosView.setHidden(true);
        dosView.setReadOnly(false);

        // 获取访问文件属主信息的FileOwnerAttributeView
        FileOwnerAttributeView ownerView = Files.getFileAttributeView(testPath, FileOwnerAttributeView.class);
        // 获取该文件所属的用户
        System.out.println("所属的用户：" + ownerView.getOwner());

        // 获取系统中guest对应的用户
        UserPrincipal guest = FileSystems.getDefault().getUserPrincipalLookupService().lookupPrincipalByName("guest");
        // 修改文件所属用户
    //  ownerView.setOwner(guest);

        // 获取访问自定义属性的UserDefinedFileAttributeView
        UserDefinedFileAttributeView userView = Files.getFileAttributeView(testPath, UserDefinedFileAttributeView.class);
        // 获取所有属性及属性值
        for (String name : userView.list()) {
            ByteBuffer buffer = ByteBuffer.allocate(userView.size(name));
            userView.read(name, buffer);
            buffer.flip();
            System.out.println(name + " -> " + Charset.defaultCharset().decode(buffer));
        }
        // 添加一些自定义属性
        userView.write("领主", Charset.defaultCharset().encode("小王子"));
        userView.write("星球", Charset.defaultCharset().encode("B-612"));
        userView.write("爱人", Charset.defaultCharset().encode("玫瑰"));
    }
}
