package ski.java8.streams;

import org.junit.Test;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class WcCollectorTest {

    @Test
    public void shouldCalculateCharsAndLines() {
        String line = "This is a line. Very long line.";
        int nlines = 10_000;
        Stream<String> text = TextGenerator.generate(line, nlines);
        //Stream<String> text = TextGenerator.infinite(line);

        long start = System.currentTimeMillis();
        Wc wc = text.parallel().collect(new WcCollector());
        System.out.printf("Collected in: %dms%n", System.currentTimeMillis()-start);

        assertEquals(line.length()*(long)nlines, wc.charCount());
        System.out.println("Word count: " + wc.wordCount());
    }
}