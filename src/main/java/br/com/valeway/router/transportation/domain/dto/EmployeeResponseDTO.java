package br.com.valeway.router.transportation.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class EmployeeResponseDTO {
    private Long id;
    private String name;
    private String cpf;
    private BigDecimal salary;
    private String address;
    private String registration;
    private String companyCnpj;
}

