package jp.co.axa.apidemo.infra.encryption;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Component;

import static jp.co.axa.apidemo.config.DataBaseEncryptionConfig.DATABASE_TEXT_ENCRYPTOR_BEAN;

@Component
public class DataBaseEncryptionStaticBeans implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        DataBaseEncryptionStaticBeans.context = context;
    }

    public static TextEncryptor databaseTextEncryptor() {
        return context.getBean(DATABASE_TEXT_ENCRYPTOR_BEAN, TextEncryptor.class);
    }
}
