package washington.domain.propose;

import lombok.ToString;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.Maps;
import washington.domain.member.Member;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Entity
@Table(name="proposed_dates")
@ToString
public class ProposedDate implements Serializable {

    public static final String FIND_BY_DATA_SPAN = "ProposedDate.findByDataSpan";

    @EmbeddedId
    private final Date date;
    private boolean reserved;
    @Embedded
    private Round round;
    @Embedded
    private Content content;
    @OneToMany
    @JoinTable(
        name="participants",
        joinColumns=@JoinColumn(name="proposed_date", referencedColumnName="id_date"),
        inverseJoinColumns=@JoinColumn(name="login_id", referencedColumnName="login_id")
    )
    @MapKeyColumn(name="time_span")
    @MapKeyEnumerated(EnumType.STRING)
    private Map<Time, List<Member>> memberMap = Maps.mutable.of(
            Time.MORNING, new ArrayList<>(),
            Time.AFTERNOON1, new ArrayList<>(),
            Time.AFTERNOON2, new ArrayList<>(),
            Time.AFTERNOON3, new ArrayList<>()
    );

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
        this.getParticipants().add(time, member);
    }

    public void remove(Time time, Member member) {
        this.getParticipants().remove(time, member);
    }

    public int countMember(Time time) {
        return this.getParticipants().count(time);
    }

    public Optional<Time> getMostManyMemberTime() {
        return this.getParticipants().getMostManyMemberTime();
    }

    public int getMaxMemberCount() {
        return this.getParticipants().getMaxMemberCount();
    }

    public boolean contains(Time time, Member member) {
        return this.getParticipants().contains(time, member);
    }

    @Transient
    private Participants participants;

    private Participants getParticipants() {
        if (this.participants == null) {
            this.participants = new Participants(this.memberMap);
        }

        return this.participants;
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
