package washington.domain.propose;

import lombok.ToString;
import washington.domain.Id;
import washington.domain.member.Member;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="proposed_date")
@ToString
public class ProposedDate implements Serializable {
    @EmbeddedId
    private final Id<ProposedDate> id = new Id<>();
    @Embedded
    private final Date date;
    @Embedded
    private Round round;
    @Embedded
    private Content content;
    @Embedded
    private Participants participants = new Participants();

    private boolean isPlanedDate;

    public ProposedDate(Date date) {
        this.date = date;
    }

    public boolean isPlanedDate() {
        return isPlanedDate;
    }

    public String getRoundAsString() {
        return this.round.asString();
    }

    public void add(Time time, Member member) {
        this.participants.add(time, member);
    }

    public void remove(Time time, Member member) {
        this.participants.remove(time, member);
    }

    public int countMember(Time time) {
        return this.participants.count(time);
    }

    public Time getMostManyMemberTime() {
        return this.participants.getMostManyMemberTime();
    }

    public int getMaxMemberCount() {
        return this.participants.getMaxMemberCount();
    }

    public boolean contains(Time time, Member member) {
        return this.participants.contains(time, member);
    }

    public void setPlanedDate(boolean planedDate) {
        isPlanedDate = planedDate;
    }

    public void setRound(Round round) {
        this.round = round;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ProposedDate)) {
            return false;
        }
        ProposedDate other = (ProposedDate) obj;

        return this.id.equals(other.id);
    }

    Date getDate() {
        return date;
    }

    Content getContent() {
        return content;
    }

    Round getRound() {
        return round;
    }

    private ProposedDate() {
        this.date = null;
    }
}
