package washington.domain.member;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@ToString
@EqualsAndHashCode
public class MemberName implements Serializable {
    @Column(name="name")
    private final String value;

    public MemberName(String value) {
        this.value = value;
    }

    private MemberName() {
        this.value = null;
    }
}
