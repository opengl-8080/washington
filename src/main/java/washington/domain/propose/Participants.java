package washington.domain.propose;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.factory.Lists;
import washington.domain.member.Member;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Iterator;

@Embeddable
@ToString
@EqualsAndHashCode
public class Participants implements Serializable, Iterable<Member> {
    private MutableList<Member> members = Lists.mutable.empty();

    @Override
    public Iterator<Member> iterator() {
        return this.members.iterator();
    }

    void add(Member member) {
        this.members.add(member);
    }

    int size() {
        return this.members.size();
    }

    boolean contains(Member member) {
        return this.members.contains(member);
    }

    void remove(Member member) {
        this.members.remove(member);
    }
}
