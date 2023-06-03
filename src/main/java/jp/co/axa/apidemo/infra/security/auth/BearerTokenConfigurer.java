package jp.co.axa.apidemo.infra.security.auth;

import jp.co.axa.apidemo.infra.security.filter.BearerTokenFilter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

import java.util.function.Function;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

public class BearerTokenConfigurer extends BaseAuthConfigurer<BearerTokenConfigurer> {

    private Function<BearerAuthentication, Authentication> authentication;

    @Override
    public void init(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .addFilterBefore(
                        new BearerTokenFilter(authentication, getRequestMatcher()),
                        AnonymousAuthenticationFilter.class
                ).sessionManagement()
                .sessionCreationPolicy(STATELESS)
                .and()
                .exceptionHandling();
    }

    @SuppressWarnings("UnusedReturnValue")
    public BearerTokenConfigurer authenticate(Function<BearerAuthentication, Authentication> authentication) {
        this.authentication = authentication;
        return this;
    }

    @Getter
    @RequiredArgsConstructor
    public static class BearerAuthentication extends AuthBase {
        private final String token;
    }
}
