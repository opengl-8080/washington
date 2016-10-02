package washington.domain.sample;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class Piyo implements Serializable {
    @Column(name="STRING_VALUE")
    private String value;
}
