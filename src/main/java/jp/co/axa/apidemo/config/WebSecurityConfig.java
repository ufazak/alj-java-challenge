package jp.co.axa.apidemo.config;

import jp.co.axa.apidemo.domain.user.User;
import jp.co.axa.apidemo.domain.user.UserRepository;
import jp.co.axa.apidemo.infra.security.TokenData;
import jp.co.axa.apidemo.infra.security.TokenService;
import jp.co.axa.apidemo.infra.security.auth.BearerAuthConfigurer;
import jp.co.axa.apidemo.infra.security.auth.UserAuth;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import java.util.NoSuchElementException;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain bearerAuthSecurityFilterChain(
            HttpSecurity http,
            TokenService tokenService,
            UserRepository userRepository
    ) throws Exception {
        http.apply(new BearerAuthConfigurer())
                .authenticate(bearer -> {
                    TokenData tokenData = tokenService.checkAndParse(bearer.getToken());
                    User user = userRepository.findById(tokenData.getUserId())
                            .orElseThrow(NoSuchElementException::new);
                    return new UserAuth(user);
                });
        return http.build();
    }
}
