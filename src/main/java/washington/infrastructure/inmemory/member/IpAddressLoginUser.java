package washington.infrastructure.inmemory.member;

import washington.domain.member.LoginId;
import washington.domain.member.LoginUser;
import washington.domain.member.Member;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

@SessionScoped
public class IpAddressLoginUser implements LoginUser, Serializable {
    @Inject
    private HttpServletRequest request;

    @Override
    public Member getMember() {
        String remoteAddr = this.request.getRemoteAddr();
        return new Member(new LoginId(remoteAddr));
    }
}
