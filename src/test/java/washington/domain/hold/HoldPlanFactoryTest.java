package washington.domain.hold;

import mockit.Expectations;
import mockit.Mocked;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.impl.factory.Lists;
import org.junit.Test;
import washington.domain.common.Date;
import washington.domain.common.DateTime;
import washington.domain.common.Time;
import washington.domain.member.LoginId;
import washington.domain.member.Member;
import washington.domain.member.MemberRepository;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

public class HoldPlanFactoryTest {

    @Mocked
    private MemberRepository memberRepository;

    @Test
    public void 指定日時で参加可能なメンバーが設定された状態で開催予定が生成される() throws Exception {
        // setup
        Date date = new Date(LocalDate.now());
        DateTime dateTime = new DateTime(date, Time.AFTERNOON2);
        Content content = new Content("test");

        ImmutableList<Member> participatableMembers = Lists.immutable.of(
            new Member(new LoginId("a")),
            new Member(new LoginId("b")),
            new Member(new LoginId("c"))
        );

        new Expectations() {{
            memberRepository.findPariticatableMembers(dateTime); result = participatableMembers;
        }};

        HoldPlanFactory factory = new HoldPlanFactory(memberRepository);

        // exercise
        HoldPlan holdPlan = factory.create(dateTime, content);

        // verify
        assertThat(holdPlan.getContent()).as("内容").isEqualTo(content);
        assertThat(holdPlan.getDateTime()).as("日時").isEqualTo(dateTime);
        assertThat(holdPlan.getParticipatableMembers()).as("参加可能メンバー")
                .containsExactlyInAnyOrder(participatableMembers.toArray(new Member[0]));
    }
}