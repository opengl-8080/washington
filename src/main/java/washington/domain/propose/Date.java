package washington.domain.propose;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
@ToString
@EqualsAndHashCode
public class Date implements Serializable {
    @Column(name="hold_date")
    private final LocalDate value;

    public Date(LocalDate value) {
        this.value = value;
    }

    private Date() {
        this.value = null;
    }
}
