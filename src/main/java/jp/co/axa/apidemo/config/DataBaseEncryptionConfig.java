package jp.co.axa.apidemo.config;

import jp.co.axa.apidemo.config.properties.DataBaseEncryptionProperties;
import jp.co.axa.apidemo.config.properties.SystemAuthProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.TextEncryptor;

import static org.springframework.security.crypto.encrypt.Encryptors.noOpText;
import static org.springframework.security.crypto.encrypt.Encryptors.text;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties({DataBaseEncryptionProperties.class, SystemAuthProperties.class})
public class DataBaseEncryptionConfig {

    public static final String DATABASE_TEXT_ENCRYPTOR_BEAN = "database_text_encryptor_bean";
    private final DataBaseEncryptionProperties properties;
    private final SystemAuthProperties systemAuthProperties;

    @Bean(DATABASE_TEXT_ENCRYPTOR_BEAN)
    public TextEncryptor databaseTextEncryptor() {
        if (properties.isEnable()) {
            return text(systemAuthProperties.getPassword(), properties.getSalt());
        } else {
            return noOpText();
        }
    }
}
