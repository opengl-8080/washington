package washington.domain.propose;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.eclipse.collections.impl.factory.Lists;
import washington.domain.member.Member;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

//@Embeddable
@ToString
@EqualsAndHashCode
public class Participants implements Serializable, Comparable<Participants> {

//    @OneToMany
//    @JoinColumn(name="login_id", referencedColumnName="login_id")
    private final List<Member> members = Lists.mutable.empty();

    public int count() {
        return this.members.size();
    }

    public void add(Member member) {
        this.members.add(member);
    }

    public void remove(Member member) {
        this.members.remove(member);
    }

    public boolean isEmpty() {
        return this.members.isEmpty();
    }

    public boolean contains(Member member) {
        return this.members.contains(member);
    }

    @Override
    public int compareTo(Participants other) {
        return this.count() - other.count();
    }
}
