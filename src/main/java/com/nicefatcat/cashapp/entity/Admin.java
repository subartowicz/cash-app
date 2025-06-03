package com.nicefatcat.cashapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "admin")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nick nie może być pusty")
    @Size(max = 5, message = "Maksymalna długoś to X znaków adminie")
    @Column(nullable = false, unique = true)
    private String username;

    @Email(message = "Niepoprawny email")
    @NotBlank(message = "Pole nie może być puste")
    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
}
