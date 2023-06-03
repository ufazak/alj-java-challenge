package jp.co.axa.apidemo.domain.employee;

import jp.co.axa.apidemo.infra.jpa.convertor.StringEncryptionConvertor;
import lombok.Data;

import javax.persistence.*;
import java.time.ZonedDateTime;

import static java.time.ZonedDateTime.now;

@Data
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = StringEncryptionConvertor.class)
    private String name;

    private Integer salary;

    @Convert(converter = StringEncryptionConvertor.class)
    private String department;

    private ZonedDateTime creationTime = now();
}
