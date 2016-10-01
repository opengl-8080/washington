package washington.domain.propose;

import lombok.ToString;
import washington.domain.member.Member;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Optional;

@Entity
@Table(name="proposed_date")
@ToString
public class ProposedDate implements Serializable {
    @EmbeddedId
    private final Date date;
    @Embedded
    private Round round;
    @Embedded
    private Content content;
    @Embedded
    private Participants participants = new Participants();

    private boolean reserved;

    public ProposedDate(Date date) {
        this.date = date;
    }

    public boolean isReserved() {
        return reserved;
    }

    public String getRoundAsString() {
        return this.round.asText();
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

    public Optional<Time> getMostManyMemberTime() {
        return this.participants.getMostManyMemberTime();
    }

    public int getMaxMemberCount() {
        return this.participants.getMaxMemberCount();
    }

    public boolean contains(Time time, Member member) {
        return this.participants.contains(time, member);
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public void setRound(Round round) {
        this.round = round;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    @Override
    public int hashCode() {
        return this.date.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ProposedDate)) {
            return false;
        }
        ProposedDate other = (ProposedDate) obj;

        return this.date.equals(other.date);
    }

    public Date getDate() {
        return date;
    }

    Content getContent() {
        return content;
    }

    public String getRoundAsText() {
        return this.round == null ? "" : this.round.asText();
    }

    private ProposedDate() {
        this.date = new Date(LocalDate.now());
    }

}
