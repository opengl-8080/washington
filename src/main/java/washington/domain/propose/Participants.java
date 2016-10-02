package washington.domain.propose;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.Maps;
import washington.domain.member.Member;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

//@Embeddable
@ToString
@EqualsAndHashCode
public class Participants implements Serializable {
//    @OneToMany
//    @JoinTable(
//        name="participants",
//        joinColumns=@JoinColumn(name="proposed_date", referencedColumnName="id_date"),
//        inverseJoinColumns=@JoinColumn(name="login_id", referencedColumnName="login_id")
//    )
//    @MapKeyColumn(name="time_span")
//    @MapKeyEnumerated(EnumType.STRING)
    private Map<Time, List<Member>> memberMap = Maps.mutable.of(
        Time.MORNING, Lists.mutable.empty(),
        Time.AFTERNOON1, Lists.mutable.empty(),
        Time.AFTERNOON2, Lists.mutable.empty(),
        Time.AFTERNOON3, Lists.mutable.empty()
    );

    Participants(Map<Time, List<Member>> memberMap) {
        this.memberMap = memberMap;
    }

    public int count(Time time) {
        return this.memberMap.get(time).size();
    }

    public void add(Time time, Member member) {
        this.memberMap.get(time).add(member);
    }

    public void remove(Time time, Member member) {
        this.memberMap.get(time).remove(member);
    }

    public Optional<Time> getMostManyMemberTime() {
        Time max = this.asEclipseCollections().keysView().max((time1, time2) -> this.count(time1) - this.count(time2));
        return (this.count(max) == 0) ? Optional.empty() : Optional.of(max);
    }

    public int getMaxMemberCount() {
        return this.asEclipseCollections().max((m1, m2) -> m1.size() - m2.size()).size();
    }

    public boolean contains(Time time, Member member) {
        return this.memberMap.get(time).contains(member);
    }

    private ImmutableMap<Time, List<Member>> asEclipseCollections() {
        return Maps.immutable.ofAll(this.memberMap);
    }
}
