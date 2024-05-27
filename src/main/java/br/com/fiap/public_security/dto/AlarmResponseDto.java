package br.com.fiap.public_security.dto;

import br.com.fiap.public_security.model.Alarm;
import jakarta.persistence.Column;

import java.time.LocalDateTime;

public record AlarmResponseDto(
        Long alarmId,
        String type,
        String status,
        LocalDateTime alarmDate
) {
    public AlarmResponseDto(Alarm alarm) {
        this(
                alarm.getAlarmId(),
                alarm.getType(),
                alarm.getStatus(),
                alarm.getAlarmDate()
        );
    }

}
