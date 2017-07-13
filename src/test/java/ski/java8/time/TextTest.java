package ski.java8.time;

import org.junit.Test;

import java.time.ZoneId;

import static org.junit.Assert.*;

public class TextTest {

    @Test
    public void shouldTranslateMeetingDate() {
        String meeting = "2017-07-13T10:00:00+02:00";
        String expected = "2017-07-13 3:00 AM CDT";

        String formatted = Text.toZone(meeting, ZoneId.of("America/Chicago"));

        assertEquals(expected, formatted);
    }
}