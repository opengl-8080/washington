package washington.domain.propose;

import lombok.ToString;
import washington.domain.Id;
import washington.domain.member.Member;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

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
    private Map<Time, Participants> participantsMap = new HashMap<>();

    private boolean isPlanedDate;

    public ProposedDate(Date date) {
        this.date = date;
        this.participantsMap.put(Time.MORNING, new Participants());
        this.participantsMap.put(Time.AFTERNOON1, new Participants());
        this.participantsMap.put(Time.AFTERNOON2, new Participants());
        this.participantsMap.put(Time.AFTERNOON3, new Participants());
    }

    public boolean isPlanedDate() {
        return isPlanedDate;
    }

    public String getRoundAsString() {
        return this.round.asString();
    }

    public void add(Time time, Member member) {
        this.participantsMap.get(time).add(member);
    }

    public void remove(Time time, Member member) {
        this.participantsMap.get(time).remove(member);
    }

    public int countParticipants(Time time) {
        return this.participantsMap.get(time).size();
    }

    public Time getMostManyParticipantsTime() {
        return this.participantsMap.entrySet().stream()
                .max((e1, e2) -> e1.getValue().size() - e2.getValue().size())
                .get().getKey();
    }

    public int getMostManyParticipantsCount() {
        return this.participantsMap.entrySet().stream()
                .max((e1, e2) -> e1.getValue().size() - e2.getValue().size())
                .get().getValue().size();
    }

    public boolean contains(Time time, Member member) {
        return this.participantsMap.get(time).contains(member);
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

    Map<Time, Participants> getParticipantsMap() {
        return participantsMap;
    }

    private ProposedDate() {
        this.date = null;
    }
}
