package washington.domain.member;

import lombok.ToString;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="members")
@ToString
public class Member implements Serializable {
    @EmbeddedId
    private final LoginId loginId;
    @Embedded
    private MemberName memberName;

    public Member(LoginId loginId) {
        this.loginId = loginId;
    }

    @Override
    public int hashCode() {
        return this.loginId.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Member)) {
            return false;
        }

        Member other = (Member) obj;

        return this.loginId.equals(other.loginId);
    }

    private Member() {
        this.loginId = null;
    }
}
