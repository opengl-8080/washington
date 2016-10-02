package washington.domain.propose;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Iterator;

@ToString
@EqualsAndHashCode
public class DateSpan implements Iterable<Date> {
    private final Date from;
    private final Date to;


    public DateSpan(Date from, Date to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public Iterator<Date> iterator() {
        return new Iterator<Date>() {
            private Date current;

            @Override
            public boolean hasNext() {
                return !DateSpan.this.to.equals(this.current);
            }

            @Override
            public Date next() {
                if (this.current == null) {
                    this.current = DateSpan.this.from;
                } else {
                    this.current = this.current.nextDate();
                }

                return this.current;
            }
        };
    }

    public Date getFrom() {
        return this.from;
    }

    public Date getTo() {
        return this.to;
    }
}
