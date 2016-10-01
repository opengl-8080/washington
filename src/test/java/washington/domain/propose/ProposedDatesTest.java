package washington.domain.propose;

import org.eclipse.collections.impl.factory.Lists;
import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;
import static washington.TestUtil.*;

public class ProposedDatesTest {

    @Test
    public void name() throws Exception {
        // setup
        ProposedDates proposedDates = new ProposedDates(Lists.mutable.of(
                proposedDate(2016, 10, 1),
                proposedDate(2016, 10, 2),
                proposedDate(2016, 10, 3)
        ));

        // exercise
        ProposedDate date = proposedDates.get(new Date(LocalDate.of(2016, 10, 2)));

        // verify
        assertThat(date).isEqualTo(proposedDate(2016, 10, 2));
    }
}