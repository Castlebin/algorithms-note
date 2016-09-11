package ch02.se02;

import org.junit.Test;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class CreateStream {
    private Logger log = Logger.getGlobal();

    private static String filePath = "src/main/resources/war-and-peace.txt";
    private String content = new String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_8);
    private String[] wordArr = content.split("[\\P{L}]+");

    public CreateStream() throws IOException {
    }

    @Test
    public void testStreamOf() throws IOException {
        long count = Stream.of(wordArr).filter(w -> w.length() > 12).count();
        log.info("count: " + count);
    }

    @Test
    public void creataStream() {
        // 创建一个空的Stream
        Stream<String> emptyStream = Stream.empty();

        // 使用Stream.of(...)方法创建包含任意数量元素的Stream
        Stream<String> song = Stream.of("gently", "down", "the", "stream");

        // 使用Stream.generate()来生成无限流
        // 创建一个包含常量值的无限Stream
        Stream<String> echos = Stream.generate(() -> "Echo");
        echos.limit(5).forEach(log::info);

        // 生成一个包含Double元素的无限流
        Stream<Double> random = Stream.generate(Math::random);
        random.limit(10).forEach(x -> log.info("" + x));

        // 使用Stream.iterate()来创建一个形如0, 1, 2, 3, ...的无限流
        Stream<BigInteger> integers = Stream.iterate(BigInteger.ZERO, n -> n.add(BigInteger.ONE));
        integers.limit(10).forEach(x -> log.info("" + x));
    }

    @Test
    public void testFileStream() throws IOException {
        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            long count = lines.count();// count line
            log.info("line count: " + count);
        }
    }

}
