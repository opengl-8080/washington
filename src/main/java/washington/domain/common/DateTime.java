package washington.domain.common;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Embeddable
@ToString
@EqualsAndHashCode
public class DateTime implements Serializable {
    @Embedded
    private final Date date;

    @Column(name="hold_time")
    @Enumerated(EnumType.STRING)
    private final Time time;

    public DateTime(Date date, Time time) {
        this.date = date;
        this.time = time;
    }

    private DateTime() {
        this.date = null;
        this.time = null;
    }
}
