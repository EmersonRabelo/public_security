package br.com.fiap.public_security.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "ps_alarms")
@Getter
@Setter
public class Alarm {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "PS_ALARMS_SEQ"
    )
    @SequenceGenerator(
            name = "PS_ALARMS_SEQ",
            sequenceName = "PS_ALARMS_SEQ",
            allocationSize = 1
    )
    @Column(name = "alarm_id")
    private Long  alarmId;
    private String type;
    private String status;
    @Column(name = "dt_alarm")
    private LocalDateTime alarmDate;

}
