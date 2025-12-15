package com.example.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;      // login email

    @Column(nullable = false)
    private String password;   // encrypted password

    @Column(nullable = true, unique = true)
    private String username;   // optional

    @Column(nullable = true, unique = true)
    private String phone;      // optional
}
