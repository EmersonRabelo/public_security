package br.com.fiap.public_security.repository;

import br.com.fiap.public_security.model.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlarmRepository extends JpaRepository<Alarm, Long> {
    public Alarm findByAlarmId(Long id);
    public List<Alarm> findByStatus(String status);
    public Alarm findByType(String type);
}
