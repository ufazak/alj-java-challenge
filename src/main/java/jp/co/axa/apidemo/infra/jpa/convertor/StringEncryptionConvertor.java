package jp.co.axa.apidemo.infra.jpa.convertor;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class StringEncryptionConvertor implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String s) {
        return s;
    }

    @Override
    public String convertToEntityAttribute(String s) {
        return s;
    }
}
