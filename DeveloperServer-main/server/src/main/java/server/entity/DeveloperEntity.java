package server.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.Set;

/**
 * Сущность Девелоперских Компаний
 *
 * Поля:
 * - developer_id - id девелоперской компании
 * - companyCode - код девелоперской компании
 * - companyName - название организации
 * - website - вебсайт
 * - phone - телефон организации
 * - email - почта организации
 */

@Entity
@Setter
@Getter
@Table(name = "developers")
@NoArgsConstructor
public class DeveloperEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long developer_id;

    @Column(nullable = false, unique = true)
    private String companyCode;

    @Column(nullable = false, unique = true)
    private String companyName;

    @Column(nullable = false, unique = true)
    private String website;

    @Column
    private String phone;

    @Column
    private String email;

    // Примечание: возможно, вы хотели использовать @ManyToOne вместо @OneToMany,
    // если связь с другой таблицей (например, таблицей с проектами)
    // Пример:
    // @ManyToOne
    // @JoinColumn(name = "project_id")
    // private ProjectEntity project;

    // Геттеры, сеттеры и другие методы, если необходимо
}