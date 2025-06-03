package com.nicefatcat.cashapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email(message = "Niepoprawny email")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Pole nie może być puste")
    @Size(max=200, message = "Maksymalna liczba znaków wynosi 200")
    @Column(nullable = false)
    private String password;

    @Size(max=20, message = "Maksymalna liczba znaków wynosi 20")
    @NotBlank(message = "Pole nie może być puste")
    @Column(name = "first_name", nullable = false, unique = false)
    private String firstName;

    @Size(max=20, message = "Maksymalna liczba znaków wynosi 20")
    @NotBlank(message = "Pole nie może być puste")
    @Column(name = "last_name", nullable = false, unique = false)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
}
