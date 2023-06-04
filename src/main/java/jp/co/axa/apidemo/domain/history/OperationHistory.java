package jp.co.axa.apidemo.domain.history;

import jp.co.axa.apidemo.infra.jpa.convertor.StringEncryptionConvertor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;

import static java.time.ZonedDateTime.now;
import static javax.persistence.EnumType.STRING;

@Data
@Entity
@NoArgsConstructor
@Table(name = "operations_history")
public class OperationHistory {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @Enumerated(STRING)
    private OperationType type;

    @Convert(converter = StringEncryptionConvertor.class)
    private String entry;

    private ZonedDateTime creationTime = now();

    public OperationHistory(Long userId, OperationType type, String entry) {
        this.userId = userId;
        this.type = type;
        this.entry = entry;
    }

    public enum OperationType {
        CREATE,
        UPDATE,
        DELETE
    }
}
