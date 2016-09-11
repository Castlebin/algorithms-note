package ch02.se06;

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
        log.info(wordStream.min(Comparator.comparing(String::length)).get());

        wordStream = words.stream();
        log.info(wordStream.max(Comparator.comparing(String::length)).get());

        wordStream = words.stream();
        log.info(wordStream.filter(s -> s.startsWith("Q")).findFirst().get());

        wordStream = words.stream();
        log.info(wordStream.parallel().filter(s -> s.startsWith("Q")).findAny().get());

        wordStream = words.stream();
        log.info(wordStream.parallel().anyMatch(s -> s.startsWith("Q"))+"");
    }

}
