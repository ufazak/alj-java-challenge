package jp.co.axa.apidemo.config;

import jp.co.axa.apidemo.domain.user.User;
import jp.co.axa.apidemo.domain.user.UserRepository;
import jp.co.axa.apidemo.infra.exception.UserNotFoundException;
import jp.co.axa.apidemo.infra.security.TokenService;
import jp.co.axa.apidemo.infra.security.TokenService.TokenData;
import jp.co.axa.apidemo.infra.security.auth.BearerTokenConfigurer;
import jp.co.axa.apidemo.infra.security.auth.PublicAuthConfigurer;
import jp.co.axa.apidemo.infra.security.auth.UserAuth;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static jp.co.axa.apidemo.infra.RequestMatchers.BEARER_TOKEN_REQUEST_MATCHERS;
import static jp.co.axa.apidemo.infra.RequestMatchers.PUBLIC_AUTH_REQUEST_MATCHERS;
import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final TokenService tokenService;
    private final UserRepository userRepository;

    @Configuration
    class BearerTokenConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.apply(new BearerTokenConfigurer())
                    .requestMatcher(BEARER_TOKEN_REQUEST_MATCHERS)
                    .authenticate(bearer -> {
                        TokenData tokenData = tokenService.checkAndParse(bearer.getToken());
                        User user = userRepository.findById(tokenData.getUserId())
                                .orElseThrow(() -> new UserNotFoundException("user not found with id - " + tokenData.getUserId()));
                        return new UserAuth(user);
                    });
        }
    }

    @Configuration
    @Order(HIGHEST_PRECEDENCE)
    @SuppressWarnings("InnerClassMayBeStatic")
    class PublicAuthConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.apply(new PublicAuthConfigurer())
                    .requestMatcher(PUBLIC_AUTH_REQUEST_MATCHERS);
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
