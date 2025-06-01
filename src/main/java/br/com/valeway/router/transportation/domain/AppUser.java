package br.com.valeway.router.transportation.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_users")
@Data
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String name;

    @Column(nullable = false, unique = true)
    private String identificationNumber;

    private String userType;

}

