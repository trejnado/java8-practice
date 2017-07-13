package ski.java8.time;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class BadFridays {

    public static LocalDate next(LocalDate from) {
        from = findNext13thOfMonth(from);
        while (from.getDayOfWeek() != DayOfWeek.FRIDAY) {
            from = from.plusMonths(1);
        }
        return from;
    }

    private static LocalDate findNext13thOfMonth(LocalDate from) {
        if (from.getDayOfMonth() >= 13) {
            from = from.plusMonths(1).withDayOfMonth(13);
        } else {
            from = from.withDayOfMonth(13);
        }
        return from;
    }
}
