package washington.domain.common;

import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="lock_table")
@ToString
public class Lock implements Serializable {
    /**一括候補日登録処理用のロック*/
    public static final Lock BATCH_REGISTER_PROPOSED_DATE = new Lock("BATCH_REGISTER_PROPOSED_DATE");

    private final String name;

    private Lock(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Lock)) {
            return false;
        }

        Lock other = (Lock) obj;
        return this.name.equals(other.name);
    }

    Lock() {
        this.name = "";
    }
}
