package washington.view.bean;

import lombok.Data;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.Maps;
import washington.domain.propose.Date;
import washington.domain.propose.DateSpan;
import washington.domain.propose.ProposedDate;
import washington.domain.propose.ProposedDateRepository;
import washington.domain.propose.ProposedDates;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Named
@Data
@RequestScoped
public class MonthCalendarBean {
    private static final DayOfWeek[] DAY_OF_WEEKS = {
            DayOfWeek.SUNDAY,
            DayOfWeek.MONDAY,
            DayOfWeek.TUESDAY,
            DayOfWeek.WEDNESDAY,
            DayOfWeek.THURSDAY,
            DayOfWeek.FRIDAY,
            DayOfWeek.SATURDAY
    };

    private Integer year;
    private Integer month;
    private List<Map<String, Cell>> weeks;

    @Inject
    private ProposedDateRepository repository;

    public void init() {
        if (this.year == null || this.month == null) {
            this.year = LocalDate.now().getYear();
            this.month = LocalDate.now().getMonthValue();
        }

        try {
            YearMonth.of(this.year, this.month);
        } catch (DateTimeException e) {
            this.year = LocalDate.now().getYear();
            this.month = LocalDate.now().getMonthValue();
        }

        LocalDate date = LocalDate.of(this.year, this.month, 1);
        List<Map<String, Cell>> weeks = Lists.mutable.empty();
        LocalDate nextMonth = date.plusMonths(1);

        ProposedDates proposedDates = this.repository.find(new DateSpan(new Date(date), new Date(nextMonth.minusDays(1))));

        loop: for (int i=0; i<6; i++) {
            MutableMap<String, Cell> week = Maps.mutable.empty();

            for (DayOfWeek dayOfWeek : DAY_OF_WEEKS)
                if (dayOfWeek == date.getDayOfWeek()) {
                    Cell cell = new Cell();
                    cell.setDay(date.getDayOfMonth());
                    String dateText = date.format(DateTimeFormatter.ISO_DATE);

                    Optional<ProposedDate> proposedDate = proposedDates.get(new Date(date));

                    proposedDate.ifPresent(pd -> {
                        cell.setDate(dateText);
                        cell.setProposed(true);
                        cell.setReserved(pd.isReserved());

                        pd.getMostManyMemberTime().ifPresent(time -> {
                            cell.setTime(time.format("$name($begin～$end時)"));
                            cell.setMemberCount(pd.getMaxMemberCount());
                        });

                        cell.setRound(pd.getRoundAsText());
                    });

                    week.put(dayOfWeek.name().toLowerCase(), cell);
                    date = date.plusDays(1);

                    if (!date.isBefore(nextMonth)) {
                        weeks.add(week);
                        break loop;
                    }
                }

            weeks.add(week);
        }

        this.weeks = weeks;
    }

    @Data
    public static class Cell {
        private String date;
        private int day;
        private boolean isProposed;
        private boolean isReserved;
        private String round;
        private String time;
        private Integer memberCount;
    }
}
