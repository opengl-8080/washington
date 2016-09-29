package washington.domain.hold;

import org.eclipse.collections.api.list.ImmutableList;
import washington.domain.common.DateTime;
import washington.domain.member.Member;
import washington.domain.member.MemberRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.Serializable;

@ApplicationScoped
public class HoldPlanFactory implements Serializable {
    private MemberRepository memberRepository;

    @Inject
    public HoldPlanFactory(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    public HoldPlan create(DateTime dateTime, Content content) {
        HoldPlan holdPlan = new HoldPlan(dateTime, content);

        ImmutableList<Member> pariticatableMembers = this.memberRepository.findPariticatableMembers(dateTime);
        holdPlan.setParticipatableMembers(pariticatableMembers);

        return holdPlan;
    }
}
