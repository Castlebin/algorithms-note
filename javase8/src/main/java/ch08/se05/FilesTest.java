package ch08.se05;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Stream;

public class FilesTest {

    @Test
    public void fileList() throws IOException {
        try(Stream<Path> entris = Files.list(Paths.get("D:/"))) {
            entris.filter(p -> !p.getFileName().toString().startsWith("."))
                    .forEach(System.out::println);
        }

        System.out.println("code");
        try(Stream<Path> entries = Files.walk(Paths.get("D:/code"))) {
            entries.forEach(System.out::println);
        }

        System.out.println("Recent files:");
        int depth = 5;
        Instant oneMonthAgo = Instant.now().minus(30, ChronoUnit.DAYS);
        try(Stream<Path> entries = Files.find(Paths.get("D:/code"), depth,
                (path, attrs) -> attrs.creationTime().toInstant().compareTo(oneMonthAgo) > 0 )) {
            entries.forEach(System.out::println);
        }
    }

}
