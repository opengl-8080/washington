package washington.domain.sample;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("sample")
@RequestScoped
public class SampleResource {

    @Inject
    private SampleEJB ejb;

    @GET
    public void sample() {
        this.ejb.execute();
    }
}
