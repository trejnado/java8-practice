package ski.java8.streams;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class TextSpliteratorTest {

    @Test
    public void shouldAdvanceToNextLine() {
        List<String> lines = asList("single line");
        TextSpliterator ts = new TextSpliterator(lines);

        StringBuilder sb = new StringBuilder();

        assertTrue(ts.tryAdvance(sb::append));
        assertEquals("single line", sb.toString());

        assertFalse(ts.tryAdvance(sb::append));
        assertEquals("single line", sb.toString());
    }

    @Test
    public void emptyShouldNotSplit() {
        List<String> lines = Collections.emptyList();
        TextSpliterator ts = new TextSpliterator(lines);

        assertNull(ts.trySplit());
    }

    @Test
    public void shouldSplitPartOfTask() {
        List<String> lines = asList("one", "two");

        TextSpliterator ts = new TextSpliterator(lines);

        Spliterator<String> fork = ts.trySplit();
        assertNotNull(fork);

        StringBuilder sb = new StringBuilder();
        assertTrue(ts.tryAdvance(sb::append));
        assertEquals("one", sb.toString());
        assertFalse(ts.tryAdvance(sb::append));
        assertEquals("one", sb.toString());

        sb = new StringBuilder();
        assertTrue(fork.tryAdvance(sb::append));
        assertEquals("two", sb.toString());
        assertFalse(fork.tryAdvance(sb::append));
        assertEquals("two", sb.toString());
    }

    @Test
    public void shouldReturnEstimatedTaskSize() {
        List<String> lines = asList("one", "two");

        TextSpliterator ts = new TextSpliterator(lines);

        assertEquals(2, ts.estimateSize());
        assertTrue(ts.tryAdvance(System.out::println));

        assertEquals(1, ts.estimateSize());
        assertTrue(ts.tryAdvance(System.out::println));

        assertEquals(0, ts.estimateSize());
    }

    @Test
    public void shouldReturnCharacteristics() {
        TextSpliterator ts = new TextSpliterator(asList("one", "two"));
        assertTrue(ts.hasCharacteristics(Spliterator.SIZED));
        assertTrue(ts.hasCharacteristics(Spliterator.SUBSIZED));
        assertFalse(ts.hasCharacteristics(Spliterator.CONCURRENT));
        assertFalse(ts.hasCharacteristics(Spliterator.DISTINCT));
        assertFalse(ts.hasCharacteristics(Spliterator.ORDERED));
    }

    @Test
    public void shouldWorkWithStream() {
        String line = "This is a line.";
        System.out.println(Arrays.toString(line.split("\\s+")));
        List<String> lines = TextGenerator.generate(line, 1_000).collect(Collectors.toList());
        Wc wc = StreamSupport.stream(new TextSpliterator(lines), true).collect(new WcCollector());

        assertEquals(lines.size()*line.length(), wc.charCount());
        System.out.println("Word count: " + wc.wordCount());
    }
}