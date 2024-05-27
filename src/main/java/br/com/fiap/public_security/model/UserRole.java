package br.com.fiap.public_security.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN("admin"),
    USER("user");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }

    @JsonCreator
    public static UserRole forValue(String value) {
        for (UserRole role : values()) {
            if (role.getRole().equalsIgnoreCase(value)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid value for UserRole: " + value);
    }

    @JsonValue
    public String toValue() {
        return this.role;
    }

}
