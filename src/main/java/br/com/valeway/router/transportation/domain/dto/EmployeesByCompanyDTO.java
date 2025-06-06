package br.com.valeway.router.transportation.domain.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class EmployeesByCompanyDTO {

    private Long companyId;

    private String companyName;

    private String cnpj;

    private List<EmployeeResponseDTO> funcionarios;

}
