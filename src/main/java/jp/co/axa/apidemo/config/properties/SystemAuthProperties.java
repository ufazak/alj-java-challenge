package jp.co.axa.apidemo.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("system-auth")
public class SystemAuthProperties {
    private String login;
    private String password;
}
