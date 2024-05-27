package br.com.fiap.public_security.dto;

import br.com.fiap.public_security.model.User;
import br.com.fiap.public_security.model.UserRole;

public record UserResponseDto(
        Long userId,
        String name,
        String email,
        UserRole role
) {
    public UserResponseDto(User user){
        this(
                user.getUserId(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );
    }
}
