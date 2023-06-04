package jp.co.axa.apidemo.infra.jpa.convertor;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import static jp.co.axa.apidemo.infra.encryption.DataBaseEncryptionStaticBeans.databaseTextEncryptor;

@Converter
public class StringEncryptionConvertor implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String s) {
        return databaseTextEncryptor().encrypt(s);
    }

    @Override
    public String convertToEntityAttribute(String s) {
        return databaseTextEncryptor().decrypt(s);
    }
}
