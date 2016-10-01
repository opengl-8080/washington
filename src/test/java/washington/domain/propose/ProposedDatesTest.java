package washington.domain.propose;

import org.eclipse.collections.impl.factory.Lists;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static washington.TestUtil.*;

public class ProposedDatesTest {

    @Test
    public void 日付を指定して候補日を取得できる() throws Exception {
        // setup
        ProposedDates proposedDates = new ProposedDates(Lists.mutable.of(
                proposedDate(2016, 10, 1),
                proposedDate(2016, 10, 2),
                proposedDate(2016, 10, 3)
        ));

        // exercise
        Optional<ProposedDate> date = proposedDates.get(new Date(LocalDate.of(2016, 10, 2)));

        // verify
        assertThat(date).hasValue(proposedDate(2016, 10, 2));
    }

    @Test
    public void 存在しない日付を指定した場合は空が返される() throws Exception {
        // setup
        ProposedDates proposedDates = new ProposedDates(Lists.mutable.of(
                proposedDate(2016, 10, 1),
                proposedDate(2016, 10, 2),
                proposedDate(2016, 10, 3)
        ));

        // exercise
        Optional<ProposedDate> date = proposedDates.get(new Date(LocalDate.of(2016, 10, 4)));

        // verify
        assertThat(date).isEmpty();
    }
}