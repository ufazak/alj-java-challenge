package jp.co.axa.apidemo.infra.security.auth;

import lombok.Getter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class BaseAuthConfigurer<T extends AbstractHttpConfigurer<T, HttpSecurity>> extends AbstractHttpConfigurer<T, HttpSecurity> {

    private RequestMatcher requestMatcher;

    @SuppressWarnings("unchecked")
    public T requestMatcher(List<String> antPaths) {
        if (antPaths.isEmpty()) {
            requestMatcher = (request) -> false;
        } else {
            requestMatcher = new OrRequestMatcher(
                    antPaths.stream()
                            .map(AntPathRequestMatcher::new)
                            .collect(Collectors.toList())
            );
        }
        return (T) this;
    }
}
