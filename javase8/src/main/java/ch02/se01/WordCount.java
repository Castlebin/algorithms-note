package ch02.se01;

import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class WordCount {
    private Logger log = Logger.getGlobal();

    private static String filePath = "src/main/resources/war-and-peace.txt";
    private String content = new String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_8);
    private List<String> words = Arrays.asList(content.split("[\\P{L}]+"));

    public WordCount() throws IOException {
    }

    @Test
    public void testStream() throws IOException {
        long begin = System.nanoTime();
        long count = words.stream().filter(w -> w.length() > 12).count();
        long end = System.nanoTime();
        log.info("count: " + count+", time: " + (end - begin));
    }

    // parallelStream耗时更长，哭死！Why?
    @Test
    public void testParallelStream() throws IOException {
        long begin = System.nanoTime();
        long count = words.parallelStream().filter(w -> w.length() > 12).count();
        long end = System.nanoTime();
        log.info("count: " + count+", time: " + (end - begin));
    }

    private Stream<String> stream = words.stream();
    private Stream<String> parallelStream = words.parallelStream();
    @Test
    public void testStream2() throws IOException {
        long begin = System.nanoTime();
        long count = stream.filter(w -> w.length() > 12).count();
        long end = System.nanoTime();
        log.info("count: " + count+", time: " + (end - begin));
    }

    // parallelStream耗时仍旧更长，哭死！Why?
    // 猜测是没有限制线程数量，线程之间切换导致耗时严重
    // 所以，不要迷信多线程和并行处理
    // 其实应该还是要看操作的，这里的操作存在竞争条件，多个线程共享一个count变量，所以导致并行处理速度反而更慢？
    // 对，这个猜测很有道理
    @Test
    public void testParallelStream2() throws IOException {
        long begin = System.nanoTime();
        long count = parallelStream.filter(w -> w.length() > 12).count();
        long end = System.nanoTime();
        log.info("count: " + count+", time: " + (end - begin));
    }

}
