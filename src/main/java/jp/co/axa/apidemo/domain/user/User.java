package jp.co.axa.apidemo.domain.user;

import jp.co.axa.apidemo.infra.jpa.convertor.StringEncryptionConvertor;
import lombok.Data;

import javax.persistence.*;
import java.time.ZonedDateTime;

import static java.time.ZonedDateTime.now;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = StringEncryptionConvertor.class)
    private String username;

    @Convert(converter = StringEncryptionConvertor.class)
    private String password;

    private ZonedDateTime creationTime = now();
}
