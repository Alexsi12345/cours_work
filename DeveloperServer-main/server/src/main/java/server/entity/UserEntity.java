package server.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * Сущность Пользователя
 * <p>
 * Поля:
 * <p>
 * id - уникальный идентификатор
 * <p>
 * login - логин
 * <p>
 * firstName - имя
 * <p>
 * lastName - фамилия
 * <p>
 * email - почта
 * <p>
 * password - пароль
 * <p>
 * phoneNumber - номер телефона
 * <p>
 * birthDate - дата рождения
 */
@Entity
@Table(name = "users")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Column(length = 40, nullable = false, unique = true)
    private String login;
    @Column(length = 40, nullable = false)
    private String firstName;
    @Column(length = 40, nullable = false)
    private String lastName;
    @Column(length = 40, nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(length = 20)
    private String phoneNumber;
    @Column
    private LocalDate birthDate;
}
