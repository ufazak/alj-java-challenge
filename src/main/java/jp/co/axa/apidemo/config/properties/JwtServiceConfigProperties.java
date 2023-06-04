package jp.co.axa.apidemo.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@Getter
@Setter
@ConfigurationProperties("auth.jwt")
public class JwtServiceConfigProperties {
    private String signature;
    private Duration tokenTtl;
}
