/**
 * @author Deska Mateusz S22176
 */

package zad3;


import java.io.IOException;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {

    private static final String URL = "http://wiki.puzzlers.org/pub/wordlists/unixdict.txt";

    public static void main(String[] args) throws IOException {
        Anagrams.readFromUrl(URL)
                .lines()
                .flatMap(Pattern.compile("\\W+")::splitAsStream)
                .distinct()
                .collect(Collectors.groupingBy(Anagrams::sortLettersAlphabetically))
                .values()
                .stream()
                .filter(l -> l.size() > 1)
                .forEach(Anagrams::print);
    }

}
