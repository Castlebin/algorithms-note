package ch02.se09;

import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class TestCollect {

    private Logger log = Logger.getGlobal();

    private static String filePath = "src/main/resources/The_Little_Prince.txt";
    private String content = new String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_8);
    private List<String> words = Arrays.asList(content.split("[\\P{L}]+"));

    public TestCollect() throws IOException {
    }

    @Test
    public void testCollectArray() {
        String[] result = words.stream().toArray(String[]::new);
        log.info(Arrays.toString(result));
    }

    @Test
    public void testCollectSet() {
        Set<String> result = words.stream().collect(Collectors.toSet());
        log.info(result.toString());
    }

    @Test
    public void testCollectList() {
        List<String> result = words.stream().collect(Collectors.toList());
        log.info(result.toString());
    }

    @Test
    public void testCollectTreeSet() {
        TreeSet<String> result = words.stream().collect(Collectors.toCollection(TreeSet::new));
        log.info(result.toString());
    }

    @Test
    public void testJoining() {
        // 直接joining
        String result = words.stream().collect(Collectors.joining());
        log.info(result);

        // 逗号分割 joining
        result = words.stream().collect(Collectors.joining(", "));
        log.info(result);
    }

}
