package jp.co.axa.apidemo.config;

import jp.co.axa.apidemo.config.properties.JwtServiceConfigProperties;
import jp.co.axa.apidemo.infra.security.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
@EnableConfigurationProperties(JwtServiceConfigProperties.class)
public class JwtServiceConfig {

    private final JwtServiceConfigProperties properties;

    @Bean
    public JwtService jwtService() {
        return new JwtService(properties.getSignature(), properties.getTokenTtl());
    }
}
