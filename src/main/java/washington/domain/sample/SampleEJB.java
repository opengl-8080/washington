package washington.domain.sample;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class SampleEJB {
    @PersistenceContext(unitName = "washington-ds")
    private EntityManager em;

    public void execute() {
        TypedQuery<Hoge> query = this.em.createQuery("select h from Hoge h", Hoge.class);

        StringBuilder sb = new StringBuilder();

        query.getResultList().forEach(hoge -> {
            sb.append(hoge).append("\n");
        });

        System.out.println(sb);
    }
}
