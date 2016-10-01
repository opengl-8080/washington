package washington.infrastructure.inmemory.propose;

import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.Maps;
import washington.domain.propose.Date;
import washington.domain.propose.DateSpan;
import washington.domain.propose.ProposedDate;
import washington.domain.propose.ProposedDateRepository;
import washington.domain.propose.ProposedDates;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class InmemoryProposedDateRepository implements ProposedDateRepository {

    private MutableMap<Date, ProposedDate> database = Maps.mutable.empty();

    @Override
    public ProposedDates find(DateSpan dateSpan) {
        MutableList<ProposedDate> list = Lists.mutable.empty();

        for (Date date : dateSpan) {
            if (this.database.containsKey(date)) {
                list.add(this.database.get(date));
            }
        }

        return new ProposedDates(list);
    }

    @Override
    public ProposedDate find(Date date) {
        return this.database.get(date);
    }

    @Override
    public boolean exists(Date date) {
        return this.database.containsKey(date);
    }

    @Override
    public void batchRegister(List<ProposedDate> proposedDateList) {
        proposedDateList.forEach(proposedDate -> {
            this.database.put(proposedDate.getDate(), proposedDate);
        });
    }

    @Override
    public void batchRemove(DateSpan dateSpan) {
        for (Date date : dateSpan) {
            this.database.remove(date);
        }
    }
}
