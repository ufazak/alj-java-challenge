package jp.co.axa.apidemo.infra.security.auth;

import jp.co.axa.apidemo.infra.security.filter.BearerTokenFilter;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

import java.util.function.Function;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

public class BearerAuthConfigurer extends AbstractHttpConfigurer<BearerAuthConfigurer, HttpSecurity> {

    private Function<BearerAuthentication, Authentication> authentication;

    @Override
    public void init(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/login")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .addFilterBefore(
                        new BearerTokenFilter(authentication),
                        AnonymousAuthenticationFilter.class
                ).sessionManagement()
                .sessionCreationPolicy(STATELESS)
                .and()
                .exceptionHandling();
    }

    public BearerAuthConfigurer authenticate(Function<BearerAuthentication, Authentication> authentication) {
        this.authentication = authentication;
        return this;
    }
}
