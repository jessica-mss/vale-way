package br.com.valeway.router.transportation.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class EmployeeRequestDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String cpf;

    @NotNull
    private BigDecimal salary;

    @NotBlank
    private String address;

    @NotBlank
    private String registration;

    @NotNull
    private Long companyId;
}

