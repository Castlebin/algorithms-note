package ch09.se05;

import java.io.*;
import java.nio.file.*;
import java.util.concurrent.*;

public class ProcessDemo {
   public static void main(String[] args) throws IOException, InterruptedException {
      ProcessBuilder builder = new ProcessBuilder(
         "grep", "-o", "[A-Za-z_][A-Za-z_0-9]*");
      builder.redirectInput(Paths.get("/usr/share/dict/words").toFile());
      builder.redirectOutput(Paths.get("identifiers.out").toFile());
      Process process = builder.start();
      process.waitFor(1, TimeUnit.MINUTES);

      ProcessBuilder pb = new ProcessBuilder("ls", "-la");
      pb.inheritIO();
      pb.start().waitFor();
   }
}
