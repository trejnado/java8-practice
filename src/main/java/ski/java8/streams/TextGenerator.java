package ski.java8.streams;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TextGenerator {

    private static String funnyShuffle(String line) {
        List<Integer> chars = line.chars().boxed().collect(Collectors.toList());
        Collections.shuffle(chars);
        return chars.stream().reduce(
                new StringBuilder(),
                (sb,i) -> sb.append((char)i.intValue()),
                StringBuilder::append)
                .toString();
    }

    public static Stream<String> generate(String line, int n) {
        return Stream.iterate(line, TextGenerator::funnyShuffle).limit(n);
    }

    public static Stream<String> infinite(String line) {
        return Stream.iterate(line, TextGenerator::funnyShuffle);
    }

    public static Stream<String> infinite(String line, long pauseMillis) {
        return Stream.iterate(line, (s) -> {
            try {
                Thread.sleep(pauseMillis);
            } catch (Exception e) {
            }
            return s;
        });
    }
}
