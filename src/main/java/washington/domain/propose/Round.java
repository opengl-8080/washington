package washington.domain.propose;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@ToString
@EqualsAndHashCode
public class Round implements Serializable {
    @Column(name="round")
    private final String value;

    public Round(String value) {
        this.value = value;
    }

    private Round() {
        this.value = null;
    }

    public String asText() {
        return this.value;
    }
}
