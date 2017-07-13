package ski.java8.time;

import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.Date;

import static org.junit.Assert.*;

public class YearsTest {

    @Test
    public void shouldFindLeapYears() {
        Date date = new Date(61538655600000L);

        assertTrue(Years.isLeap(date));
    }

    @Test
    public void shouldFindLastYearDateWithTheSameDay() {
        //Current date: 2017-03-31 (Friday)
        //Last year date: 2016-04-01 (Friday)

        LocalDate date = LocalDate.of(2017, Month.MARCH, 31);
        LocalDate expected = LocalDate.of(2016, Month.APRIL, 1);

        assertEquals(expected, Years.lastYearSameDay(date));
    }
}