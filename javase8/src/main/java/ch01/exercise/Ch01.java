package ch01.exercise;

import org.junit.Test;

import java.io.File;
import java.util.Arrays;

public class Ch01 {

    // NO.1 是的

    // NO.2
    @Test
    public void listSubDirectory() {
        String dir = "/home/heller";
        File file = new File(dir);
        File[] suDirs = file.listFiles(f -> f.isDirectory());
        System.out.println(Arrays.toString(suDirs));
        suDirs = file.listFiles(File::isDirectory);
        System.out.println(Arrays.toString(suDirs));
    }
    // NO.3
    @Test
    public void listSomeExtFile() {
        String dir = "/home/heller/Documents";
        File file = new File(dir);
        File[] pdfFiles = file.listFiles((dir1, name) -> name.toLowerCase().endsWith(".pdf"));
        System.out.println(Arrays.toString(pdfFiles));
    }


}
