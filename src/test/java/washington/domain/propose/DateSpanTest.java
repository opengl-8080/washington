package washington.domain.propose;

import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class DateSpanTest {

    @Test
    public void 指定期間内の日付をイテレータで反復処理できる() throws Exception {
        // setup
        Date from = new Date(LocalDate.of(2016, 10, 1));
        Date to = new Date(LocalDate.of(2016, 10, 7));
        DateSpan dateSpan = new DateSpan(from, to);

        // exercise
        List<Date> actual = new ArrayList<>();

        for (Date date : dateSpan) {
            actual.add(date);
        }

        // verify
        assertThat(actual).containsExactly(
            new Date(LocalDate.of(2016, 10, 1)),
            new Date(LocalDate.of(2016, 10, 2)),
            new Date(LocalDate.of(2016, 10, 3)),
            new Date(LocalDate.of(2016, 10, 4)),
            new Date(LocalDate.of(2016, 10, 5)),
            new Date(LocalDate.of(2016, 10, 6)),
            new Date(LocalDate.of(2016, 10, 7))
        );
    }
}