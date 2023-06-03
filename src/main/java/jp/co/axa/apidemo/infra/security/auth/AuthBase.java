package jp.co.axa.apidemo.infra.security.auth;

import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.util.Collections;

public abstract class AuthBase extends AbstractAuthenticationToken {

    public AuthBase() {
        super(Collections.emptyList());
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
