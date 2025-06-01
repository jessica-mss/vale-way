package br.com.valeway.router.transportation.domain.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RegisterDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String userType;

    @NotBlank
    private String identificationNumber;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

}

