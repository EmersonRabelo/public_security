package br.com.fiap.public_security.repository;

import br.com.fiap.public_security.dto.EmergencyResponseDto;
import br.com.fiap.public_security.model.Emergency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EmergencyRepository extends JpaRepository<Emergency, Long> {
    public List<EmergencyResponseDto> findByRequesterEmail(String requesterEmail);
    public List<EmergencyResponseDto> findByRequestDateBetween(LocalDateTime dateTimeInicial, LocalDateTime dateTimeFinal);
}
