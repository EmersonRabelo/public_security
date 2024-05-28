package br.com.fiap.public_security.dto;

import jakarta.validation.constraints.NotBlank;

public record AlarmRegisterDto(
        @NotBlank(message = "Type is required.")
        String type,
        @NotBlank(message = "Status is required.")
        String status
) {
}
