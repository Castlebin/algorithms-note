package ch02.se01;

import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

public class TestStream {
    private Logger log = Logger.getGlobal();

    private static String filePath = "src/main/resources/war-and-peace.txt";
    private String content = new String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_8);
    private List<String> words = Arrays.asList(content.split("[\\P{L}]+"));

    public TestStream() throws IOException {
    }

    @Test
    public void testStream() throws IOException {
        Map<String, String> map = new ConcurrentHashMap<>();
        long begin = System.nanoTime();
        words.stream().forEach(x -> map.put(x,x));
        long end = System.nanoTime();
        log.info("time: " + (end - begin));
    }

    // parallelStream耗时更长，哭死！Why?
    @Test
    public void testParallelStream() throws IOException {
        Map<String, String> map = new ConcurrentHashMap<>();
        long begin = System.nanoTime();
        words.parallelStream().forEach(x -> map.put(x,x));
        long end = System.nanoTime();
        log.info("time: " + (end - begin));
    }

}
