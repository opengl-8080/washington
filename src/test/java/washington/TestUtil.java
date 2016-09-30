package washington;

import washington.domain.member.LoginId;
import washington.domain.member.Member;

public class TestUtil {

    public static Member member(String loginId) {
        return new Member(new LoginId(loginId));
    }

    private TestUtil() {}
}
