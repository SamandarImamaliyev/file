package fileNIO;

import java.io.IOException;
import java.nio.file.*;

public class NIOCopy {
    public static void main(String[] args) {
        Path from = Paths.get("a.txt");
        Path to = Paths.get("aCopy.txt");
        try {
            Files.copy(from, to, StandardCopyOption.COPY_ATTRIBUTES);
        } catch (IOException e) {
            System.out.println("I/O Error " + e);
        }
    }
}
