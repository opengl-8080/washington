package washington.domain.propose;

import java.util.List;

public interface ProposedDateRepository {

    ProposedDates find(DateSpan dateSpan);
    ProposedDate find(Date date);
    boolean exists(Date date);
    void batchRegister(List<ProposedDate> proposedDateList);
    void batchRemove(DateSpan dateSpan);
}
