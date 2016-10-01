package washington.application.propose;

import washington.domain.propose.BatchRegisterProposedDate;
import washington.domain.propose.Date;
import washington.domain.propose.DateSpan;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.time.LocalDate;

@Stateless
public class BatchRegisterProposedDateService implements Serializable {

    @Inject
    private BatchRegisterProposedDate batchRegisterProposedDate;

    public void execute(LocalDate from, LocalDate to) {
        Date fromDate = new Date(from);
        Date toDate = new Date(to);
        DateSpan dateSpan = new DateSpan(fromDate, toDate);

        this.batchRegisterProposedDate.execute(dateSpan);
    }
}
