package washington.domain.hold;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.factory.Lists;
import washington.domain.member.Member;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

@Embeddable
@ToString
@EqualsAndHashCode
public class ParticipatableMembers implements Serializable, Iterable<Member> {
    private final MutableList<Member> members;

    ParticipatableMembers(List<Member> members) {
        this.members = Lists.mutable.ofAll(members);
    }

    @Override
    public Iterator<Member> iterator() {
        return this.members.iterator();
    }
}
