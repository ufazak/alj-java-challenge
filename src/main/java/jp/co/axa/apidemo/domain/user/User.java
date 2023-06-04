package jp.co.axa.apidemo.domain.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;

import static java.time.ZonedDateTime.now;

@Data
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private ZonedDateTime creationTime = now();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
