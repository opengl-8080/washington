package washington.domain.calendar;

import org.eclipse.collections.api.list.ImmutableList;

import java.time.YearMonth;

public class CalendarMonth {
    private YearMonth yearMonth;
    private ImmutableList<CalendarWeek> weeks;
}
