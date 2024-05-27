package br.com.fiap.public_security.dto;

import br.com.fiap.public_security.model.Emergency;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EmergencyResponseDto(
        Long emergencyId,
        String requesterEmail,
        String address,
        String type,
        String title,
        String description,

        String status
) {
    public EmergencyResponseDto(Emergency emergency) {
        this(
                emergency.getEmergencyId(),
                emergency.getRequesterEmail(),
                emergency.getAddress(),
                emergency.getType(),
                emergency.getTitle(),
                emergency.getDescription(),
                emergency.getStatus()
        );
    }
}
