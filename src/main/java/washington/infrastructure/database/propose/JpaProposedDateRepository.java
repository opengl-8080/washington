package washington.infrastructure.database.propose;

import washington.domain.propose.Date;
import washington.domain.propose.DateSpan;
import washington.domain.propose.ProposedDate;
import washington.domain.propose.ProposedDateRepository;
import washington.domain.propose.ProposedDates;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@ApplicationScoped
public class JpaProposedDateRepository implements ProposedDateRepository {

    @PersistenceContext(unitName = "washington-ds")
    private EntityManager em;

    @Override
    public ProposedDates find(DateSpan dateSpan) {
        TypedQuery<ProposedDate> query = this.em.createNamedQuery(ProposedDate.FIND_BY_DATA_SPAN, ProposedDate.class);
        query.setParameter("from", dateSpan.getFrom().getValue());
        query.setParameter("to", dateSpan.getTo().getValue());

        return new ProposedDates(query.getResultList());
    }

    @Override
    public ProposedDate find(Date date) {
        ProposedDate proposedDate = this.em.find(ProposedDate.class, date);
        return proposedDate;
    }

    @Override
    public boolean exists(Date date) {
        ProposedDate proposedDate = this.find(date);
        return proposedDate != null;
    }

    @Override
    public void batchRegister(List<ProposedDate> proposedDateList) {
        proposedDateList.forEach(this.em::persist);
    }

    @Override
    public void batchRemove(DateSpan dateSpan) {
        for (Date date : dateSpan) {
            ProposedDate proposedDate = this.find(date);
            this.em.remove(proposedDate);
        }
    }
}
