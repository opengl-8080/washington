package washington.domain.propose;

import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.factory.Lists;
import washington.domain.common.Lock;
import washington.domain.common.LockService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.Serializable;

@ApplicationScoped
public class BatchRegisterProposedDate implements Serializable {

    @Inject
    private ProposedDateRepository repository;
    @Inject
    private LockService lockService;

    public void execute(DateSpan dateSpan) {
        this.lockService.lock(Lock.BATCH_REGISTER_PROPOSED_DATE);

        MutableList<ProposedDate> proposedDateList = Lists.mutable.empty();

        for (Date date : dateSpan) {
            if (!date.isSaturdayOrSunday() && !this.repository.exists(date)) {
                proposedDateList.add(new ProposedDate(date));
            }
        }

        this.repository.batchRegister(proposedDateList);
    }
}
