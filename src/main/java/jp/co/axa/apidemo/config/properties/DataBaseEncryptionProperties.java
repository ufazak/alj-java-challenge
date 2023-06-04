package jp.co.axa.apidemo.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("encryption")
public class DataBaseEncryptionProperties {
    private boolean enable;
    private String salt;
}
