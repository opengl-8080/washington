package washington.domain.member;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@ToString
@EqualsAndHashCode
public class LoginId implements Serializable {
    @Column(name="login_id")
    private final String value;

    public LoginId(String value) {
        this.value = value;
    }

    private LoginId() {
        this.value = null;
    }
}
