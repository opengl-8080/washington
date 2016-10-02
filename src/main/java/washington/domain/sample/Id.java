package washington.domain.sample;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Embeddable
@Data
public class Id {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
