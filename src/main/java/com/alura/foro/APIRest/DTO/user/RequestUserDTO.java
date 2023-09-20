package com.alura.foro.APIRest.DTO.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestUserDTO(
        @NotBlank @NotNull
        String email,
        @NotBlank @NotNull

        String username,
        @NotBlank @NotNull

        String password) {
}
