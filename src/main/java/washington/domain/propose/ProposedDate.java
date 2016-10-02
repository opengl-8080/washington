package washington.domain.propose;

import lombok.ToString;
import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.impl.factory.Maps;
import washington.domain.member.Member;

import javax.mail.Part;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
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
    @Transient
//    @Embedded
//    @ElementCollection
//    @CollectionTable(
//        name="participants",
//        joinColumns=@JoinColumn(name="proposed_date", referencedColumnName="id_date")
//    )
//    @MapKeyColumn(name="time_span")
//    @MapKeyEnumerated(EnumType.STRING)
    private Map<Time, Participants> memberMap = Maps.mutable.of(
        Time.MORNING, new Participants(),
        Time.AFTERNOON1, new Participants(),
        Time.AFTERNOON2, new Participants(),
        Time.AFTERNOON3, new Participants()
    );

    public ProposedDate(Date date) {
        this.date = date;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void add(Time time, Member member) {
        this.memberMap.get(time).add(member);
    }

    public void remove(Time time, Member member) {
        this.memberMap.get(time).remove(member);
    }

    public int countMember(Time time) {
        return this.memberMap.get(time).count();
    }

    public Optional<Time> getMostManyMemberTime() {
        Time max = this.withEclipseCollection().keysView().max(this::compareByMemberCount);
        return this.countMember(max) == 0 ? Optional.empty() : Optional.of(max);
    }

    private ImmutableMap<Time, Participants> withEclipseCollection() {
        return Maps.immutable.ofAll(this.memberMap);
    }

    private int compareByMemberCount(Time t1, Time t2) {
        return this.memberMap.get(t1).compareTo(this.memberMap.get(t2));
    }

    public int getMaxMemberCount() {
        Time max = this.withEclipseCollection().keysView().max(this::compareByMemberCount);
        return this.countMember(max);
    }

    public boolean contains(Time time, Member member) {
        return this.memberMap.get(time).contains(member);
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
