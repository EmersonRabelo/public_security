package br.com.fiap.public_security.dto;

import br.com.fiap.public_security.model.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRegisterDto(
        Long userId,

        @NotBlank(message = "Name is required.")
        String name,

        @NotBlank(message = "E-mail is required.")
        @Email(message = "Invalid email format.")
        String email,

        @NotBlank(message = "Password is required.")
        @Size(
                min = 12,
                max = 24,
                message = "The password must contain 12 to 24 characters."
        )
        String password,

        UserRole role
) {
}
