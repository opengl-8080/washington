package washington.domain.propose;

import java.util.List;

public interface ProposedDateRepository {

    boolean exists(Date date);
    void batchRegister(List<ProposedDate> proposedDateList);
}
