package jp.co.axa.apidemo.infra.security.auth;

import jp.co.axa.apidemo.domain.user.User;
import lombok.Getter;

@Getter
public class UserAuth extends AuthBase {

    @SuppressWarnings("FieldCanBeLocal")
    private final User user;

    public UserAuth(User user) {
        this.user = user;
        this.setAuthenticated(true);
    }
}
