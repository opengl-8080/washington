package washington.domain.hold;

import lombok.ToString;
import org.eclipse.collections.api.list.ImmutableList;
import washington.domain.common.Id;
import washington.domain.common.DateTime;
import washington.domain.member.Member;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="hold_plan")
@ToString
public class HoldPlan implements Serializable {
    @EmbeddedId
    private final Id<HoldPlan> id = new Id<>();
    @Embedded
    private final DateTime holdDateTime;
    @Embedded
    private Content content;
    @Embedded
    private ParticipatableMembers participatableMembers;

    public HoldPlan(DateTime holdDateTime, Content content) {
        this.holdDateTime = holdDateTime;
        this.content = content;
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof HoldPlan)) {
            return false;
        }
        HoldPlan other = (HoldPlan) obj;

        return this.id.equals(other.id);
    }

    private HoldPlan() {
        this.holdDateTime = null;
    }

    DateTime getDateTime() {
        return this.holdDateTime;
    }

    Content getContent() {
        return content;
    }

    ParticipatableMembers getParticipatableMembers() {
        return participatableMembers;
    }

    void setParticipatableMembers(ImmutableList<Member> particiatableMembers) {
        this.participatableMembers = new ParticipatableMembers(particiatableMembers.toList());
    }
}
