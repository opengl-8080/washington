package washington.domain.member;

import org.eclipse.collections.api.list.ImmutableList;
import washington.domain.common.DateTime;

public interface MemberRepository {

    ImmutableList<Member> findPariticatableMembers(DateTime dateTime);
}
