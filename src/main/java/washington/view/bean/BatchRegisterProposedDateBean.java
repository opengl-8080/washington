package washington.view.bean;

import lombok.Data;

import washington.application.propose.BatchRegisterProposedDateService;
import washington.domain.propose.Date;
import washington.domain.propose.DateSpan;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Named
@Data
@ViewScoped
public class BatchRegisterProposedDateBean implements Serializable {

    private String fromDate;
    private String toDate;

    @Inject
    private BatchRegisterProposedDateService service;

    @PostConstruct
    private void init() {
        LocalDate now = LocalDate.now();
        this.fromDate = now.format(DateTimeFormatter.ISO_DATE);
        this.toDate = this.fromDate;
    }

    public void submit() {
        this.service.execute(LocalDate.parse(this.fromDate), LocalDate.parse(this.toDate));

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "登録しました", null));
    }
}
