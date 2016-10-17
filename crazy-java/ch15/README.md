# 第15章 输入/输出 (I/O)
## 15.1 File类
    15.1.1 访问文件和目录
    15.2.2 文件过滤器



## 15.9 NIO (主要包含两大部分组件Buffer和Channel)
    15.9.1 Java NIO概述
    15.9.2 使用Buffer
    15.9.3 使用Channel
    
## 15.10 Java 7的NIO.2
    Java 7对原有的NIO进行了重大改进，主要分为两个方面：
    1. 提供了全面的文件IO和文件系统访问支持
        Java 7 新增的包java.nio.file及子包
    2. 基于异步Channel的IO
        Java 7的java.nio.channels包下新增了多个以Asynchronous开头的Channel接口和类
    
    - Path, Paths和Files核心API
    - 使用FileVistor遍历文件和目录
    - 使用WatchService监控文件变化
    - Java 7的NIO.2在java.nio.file.attribute包下提供了大量的工具类，方便的来访问和修改文件属性
    