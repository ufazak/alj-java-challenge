package jp.co.axa.apidemo.infra.security;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthHolderService<T extends Authentication> {

    public T auth() {
        T auth = authOrNull();
        if (Objects.nonNull(auth)) {
            return auth;
        } else {
            throw new BadCredentialsException("authentication context is empty");
        }
    }

    @SuppressWarnings("unchecked")
    public T authOrNull() {
        return (T) SecurityContextHolder.getContext().getAuthentication();
    }
}
