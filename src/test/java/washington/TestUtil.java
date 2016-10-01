package washington;

import washington.domain.member.LoginId;
import washington.domain.member.Member;
import washington.domain.propose.Date;
import washington.domain.propose.ProposedDate;

import java.time.LocalDate;

public class TestUtil {

    public static Member member(String loginId) {
        return new Member(new LoginId(loginId));
    }

    public static ProposedDate proposedDate(int year, int month, int day) {
        return new ProposedDate(new Date(LocalDate.of(year, month, day)));
    }

    private TestUtil() {}
}
