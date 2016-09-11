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
        long count = words.stream().filter(w -> w.length() > 12).count();
        log.info("count: " + count);
    }

    @Test
    public void testParallelStream() throws IOException {
        long count = words.parallelStream().filter(w -> w.length() > 12).count();
        log.info("count: " + count);
    }

    @Test
    public void testStreamOf() throws IOException {
        long count = Stream.of(content.split("[\\P{L}+]")).filter(w -> w.length() > 12).count();
        log.info("count: " + count);
    }

}
