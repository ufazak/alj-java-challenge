package jp.co.axa.apidemo.infra.security.auth;

import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.util.Collections;

@Getter
public class BearerAuthentication extends AbstractAuthenticationToken {

    private final String token;

    public BearerAuthentication(String token) {
        super(Collections.emptyList());
        this.token = token;
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
