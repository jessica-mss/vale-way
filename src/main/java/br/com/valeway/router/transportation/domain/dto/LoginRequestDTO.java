package br.com.valeway.router.transportation.domain.dto;

import jakarta.validation.constraints.NotNull;

public record LoginRequestDTO(@NotNull String email, String password) {
}
