package zad2;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Futil {

    private static final Charset GIVEN_FILE_ENCODING = StandardCharsets.UTF_8;

    private static final Charset FINISH_FILE_ENCODING = Charset.forName("Cp1250");

    private static final String NEW_LINE = "\n";

    public static void processDir(String dirName, String resultFileName) {
        try (BufferedWriter out = bufferedWriter(resultFileName)) {
            Files.walk(Paths.get(dirName))
                    .filter(Files::isRegularFile)
                    .forEach(path -> writeToFile(out, path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeToFile(BufferedWriter out, Path path) {
        try {
            Files.lines(path, GIVEN_FILE_ENCODING)
                    .forEach(line -> writeLine(out, line));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeLine(BufferedWriter out, String line) {
        try {
            out.write(line + NEW_LINE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static BufferedWriter bufferedWriter(String resultFileName) throws FileNotFoundException {
        return new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(new File(resultFileName)), FINISH_FILE_ENCODING));
    }
}
