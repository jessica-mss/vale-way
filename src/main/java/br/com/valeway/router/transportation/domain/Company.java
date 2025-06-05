package br.com.valeway.router.transportation.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_companies")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String companyName;

    @Column(nullable = false, unique = true)
    private String cnpj;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String contactEmail;

    @Column(nullable = false)
    private String contactPhone;

    @Column(nullable = false)
    private String contactPersonName;

    @Column(nullable = false)
    private String status;
}