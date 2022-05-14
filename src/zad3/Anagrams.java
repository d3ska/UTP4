package zad3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Anagrams {

    public static String sortLettersAlphabetically(String s) {
        return Stream.of(s.split("")).sorted().collect(Collectors.joining());
    }

    public static BufferedReader readFromUrl(final String url) throws IOException {
        return new BufferedReader(
                new InputStreamReader(new URL(url).openStream(), StandardCharsets.UTF_8));
    }

    public static void print(List<String> anagrams) {
        System.out.println(String.join(" ", anagrams));
    }
}
