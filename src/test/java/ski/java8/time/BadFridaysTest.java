package ski.java8.time;

import org.junit.Test;

import java.time.LocalDate;

import static java.time.Month.JANUARY;
import static java.time.Month.OCTOBER;
import static org.junit.Assert.assertEquals;

public class BadFridaysTest {

    @Test
    public void shouldFindTheNextFriday13th() {
        LocalDate from = LocalDate.of(2017, JANUARY, 1);
        LocalDate expected = LocalDate.of(2017, JANUARY, 13);

        assertEquals(expected, BadFridays.next(from));
    }

    @Test
    public void shouldFindNextStartingFromFriday13th() {
        LocalDate from = LocalDate.of(2017, JANUARY, 13);
        LocalDate expected = LocalDate.of(2017, OCTOBER, 13);

        assertEquals(expected, BadFridays.next(from));
    }
}