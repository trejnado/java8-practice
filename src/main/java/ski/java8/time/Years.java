package ski.java8.time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

public class Years {

    public static boolean isLeap(Date date) {
        return Year.isLeap(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).getYear());
    }

    public static LocalDate lastYearSameDay(LocalDate thisYear) {
        return thisYear
                .minusYears(1)
                .with(TemporalAdjusters.nextOrSame(thisYear.getDayOfWeek()));
    }
}
