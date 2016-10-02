package washington.domain.sample;

import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Map;

@Entity
@Data
public class Hoge implements Serializable {
    @EmbeddedId
    private Id id;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name="FUGA",
        joinColumns=@JoinColumn(name="HOGE_ID", referencedColumnName="ID"),
        inverseJoinColumns=@JoinColumn(name="ID", referencedColumnName="HOGE_ID")
    )
    @MapKeyColumn(name="MY_ENUM")
    @MapKeyEnumerated(EnumType.STRING)
    private Map<MyEnum, Fuga> map;
}
