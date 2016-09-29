package washington.domain.propose;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@ToString
@EqualsAndHashCode
public class Content implements Serializable {
    @Column(name="content")
    private final String value;

    public Content(String value) {
        this.value = value;
    }

    private Content() {
        this.value = null;
    }
}
