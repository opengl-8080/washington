package washington.domain.sample;

import lombok.Data;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class Fuga implements Serializable {
    @EmbeddedId
    private Id id;

    @Embedded
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
        name="FUGA_LIST",
        joinColumns=@JoinColumn(name="FUGA_ID", referencedColumnName="ID")
    )
    private List<Piyo> value;
}
