package ski.java8.time;

import org.junit.Test;

import java.time.ZoneId;

import static org.junit.Assert.*;

public class TextTest {

    @Test
    public void shouldTranslateMeetingDate() {
        String meeting = "2017-07-13T10:00:00+02:00";
        String expected = "2017-07-13 02:00 AM CET";

        String formatted = Text.toZone(meeting, ZoneId.of("CET"));

        assertEquals(expected, formatted);
    }
}