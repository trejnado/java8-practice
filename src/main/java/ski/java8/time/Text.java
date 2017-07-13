package ski.java8.time;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Text {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a z");

    public static String toZone(String meeting, ZoneId zone) {
        return ZonedDateTime.parse(meeting).withZoneSameInstant(zone).format(FORMATTER);
    }
}
