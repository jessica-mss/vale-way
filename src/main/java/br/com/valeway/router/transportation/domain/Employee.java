package br.com.valeway.router.transportation.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    @Column(unique = true)
    private String cpf;

    @NotNull
    private BigDecimal salary;

    @NotBlank
    private String address;

    @NotBlank
    @Column(unique = true)
    private String registration;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;
}

