package washington.domain.propose;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Embeddable
@ToString
@EqualsAndHashCode
public class Date implements Serializable {
    @Column(name="id_date")
    private final LocalDate value;

    public Date(LocalDate value) {
        this.value = value;
    }

    private Date() {
        this.value = null;
    }

    public Date nextDate() {
        return new Date(this.value.plusDays(1));
    }

    public boolean isSaturdayOrSunday() {
        DayOfWeek dayOfWeek = this.value.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }

    public LocalDate getValue() {
        return this.value;
    }
}
