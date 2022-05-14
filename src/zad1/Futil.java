package zad1;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class Futil {

    private static final String GIVEN_FILE_ENCODING = "Windows-1250";

    private static final Charset FINISH_FILE_ENCODING = StandardCharsets.UTF_8;

    private static final String NEW_LINE = "\n";

    public static void processDir(String dirName, String resultFileName) {
        try (BufferedWriter out = bufferedWriter(resultFileName)) {
            FileVisitor<Path> fileVisitor = getFileVisitor(out);
            FileSystem fileSystem = FileSystems.getDefault();
            Path rootPath = fileSystem.getPath(dirName);
            Files.walkFileTree(rootPath, fileVisitor);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static BufferedReader bufferedReader(Path visitedFilePath) throws UnsupportedEncodingException, FileNotFoundException {
        return new BufferedReader(new InputStreamReader(new FileInputStream(visitedFilePath.toFile()), GIVEN_FILE_ENCODING));
    }

    private static BufferedWriter bufferedWriter(String resultFileName) throws FileNotFoundException {
        return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(resultFileName)), FINISH_FILE_ENCODING));
    }

    private static FileVisitor<Path> getFileVisitor(BufferedWriter out) {
        return new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path visitedFilePath, BasicFileAttributes fileAttributes)
                    throws IOException {
                try (BufferedReader in = bufferedReader(visitedFilePath)) {
                    String str;
                    while ((str = in.readLine()) != null) {
                        out.write(str + NEW_LINE);
                    }
                }
                return FileVisitResult.CONTINUE;
            }
        };
    }

}
