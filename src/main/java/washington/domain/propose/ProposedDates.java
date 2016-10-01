package washington.domain.propose;

import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.impl.factory.Maps;

import java.util.Collection;

public class ProposedDates {

    private final ImmutableMap<Date, ProposedDate> proposedDateMap;

    public ProposedDates(Collection<ProposedDate> proposedDates) {
        MutableMap<Date, ProposedDate> map = Maps.mutable.empty();

        proposedDates.forEach(proposedDate -> {
            map.put(proposedDate.getDate(), proposedDate);
        });

        this.proposedDateMap = map.toImmutable();
    }

    public ProposedDate get(Date date) {
        return this.proposedDateMap.get(date);
    }
}
