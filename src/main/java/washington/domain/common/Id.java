package washington.domain.common;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;

@Embeddable
@ToString
@EqualsAndHashCode
public class Id<T> implements Serializable {
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private final Long id = null;
}
