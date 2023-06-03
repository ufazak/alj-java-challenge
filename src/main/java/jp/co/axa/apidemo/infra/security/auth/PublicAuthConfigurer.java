package jp.co.axa.apidemo.infra.security.auth;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

public class PublicAuthConfigurer extends BaseAuthConfigurer<PublicAuthConfigurer> {

    @Override
    public void init(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .requestMatcher(getRequestMatcher())
                .authorizeRequests()
                .anyRequest()
                .permitAll()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(STATELESS);
    }
}
