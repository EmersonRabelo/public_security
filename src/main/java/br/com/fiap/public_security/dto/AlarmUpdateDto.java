package br.com.fiap.public_security.dto;

import jakarta.validation.constraints.NotBlank;

public record AlarmUpdateDto(
        @NotBlank(message = "Id is required.")
        Long alarmId,
        @NotBlank(message = "Type is required.")
        String type,
        @NotBlank(message = "Status is required.")
        String status
) {
}
