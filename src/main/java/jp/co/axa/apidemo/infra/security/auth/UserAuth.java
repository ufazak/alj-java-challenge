package jp.co.axa.apidemo.infra.security.auth;

import jp.co.axa.apidemo.domain.user.User;
import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.util.Collections;

public class UserAuth extends AbstractAuthenticationToken {

    @SuppressWarnings("FieldCanBeLocal")
    private final User user;

    public UserAuth(User user) {
        super(Collections.emptyList());
        this.user = user;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }
}
