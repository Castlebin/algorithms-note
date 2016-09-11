package ch02.se05;

import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class TestStream {

    private Logger log = Logger.getGlobal();

    private static String filePath = "src/main/resources/war-and-peace.txt";
    private String content = new String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_8);
    private List<String> words = Arrays.asList(content.split("[\\P{L}]+"));

    public TestStream() throws IOException {
    }

    @Test
    public void testStream() {
        Stream<String> wordStream = words.stream();
        // Stream.distinct()提取不含重复元素的流
        Stream<String> uniqueWords = wordStream.distinct();
        log.info("unique words count: " + uniqueWords.count());

        wordStream = words.stream();
        // Stream.sorted()返回一个新的已排序的流
        Stream<String> sortedStream = wordStream.sorted();
        sortedStream.limit(5).forEach(log::info);

        wordStream = words.stream();
        // Stream.sorted()返回一个新的已排序的流(根据字符串长度排序)
        Stream<String> lengthSortedStream = wordStream.sorted(Comparator.comparing(String::length));
        lengthSortedStream.limit(5).forEach(log::info);

        wordStream = words.stream();
        // Stream.sorted()返回一个新的已排序的流(根据字符串长度排序，倒排)
        Stream<String> longSortedStream = wordStream.sorted(Comparator.comparing(String::length).reversed());
        longSortedStream.limit(5).forEach(log::info);
    }

}
