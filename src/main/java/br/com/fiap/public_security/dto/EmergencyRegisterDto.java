package br.com.fiap.public_security.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record EmergencyRegisterDto(
        @NotBlank(message = "E-mail is required.")
        @Email(message = "Invalid email format.")
        String requesterEmail,
        @NotBlank(message = "Address is required.")
        String address,
        @NotBlank(message = "Type is required.")
        String type,

        @NotBlank(message = "Title is required.")
        String title,
        @NotNull(message = "Description is required.")
        String description,

        @NotBlank(message = "Status is required.")
        String status

) {
}
