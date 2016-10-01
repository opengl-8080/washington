package washington.domain.propose;

import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

public class DateTest {

    @Test
    public void 翌日を取得できる() throws Exception {
        // setup
        Date date = new Date(LocalDate.of(2016, 1, 1));

        // exercise
        Date nextDate = date.nextDate();

        // verify
        assertThat(nextDate).isEqualTo(new Date(LocalDate.of(2016, 1, 2)));
    }

    @Test
    public void 土日かどうか判定できる_土曜の場合() throws Exception {
        // setup
        Date saturday = new Date(LocalDate.of(2016, 10, 1));

        // exercise
        boolean actual = saturday.isSaturdayOrSunday();

        // verify
        assertThat(actual).isTrue();
    }

    @Test
    public void 土日かどうか判定できる_月曜の場合() throws Exception {
        // setup
        Date saturday = new Date(LocalDate.of(2016, 10, 3));

        // exercise
        boolean actual = saturday.isSaturdayOrSunday();

        // verify
        assertThat(actual).isFalse();
    }
}