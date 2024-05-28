package br.com.fiap.public_security.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "alarms_ps_v2")
@Getter
@Setter
public class Alarm {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ALARMS_SEQ_PS_V2"
    )
    @SequenceGenerator(
            name = "ALARMS_SEQ_PS_V2",
            sequenceName = "ALARMS_SEQ_PS_V2",
            allocationSize = 1
    )
    @Column(name = "alarm_id")
    private Long alarmId;

    private String type;

    @Column(name = "STATUS_ALARM")
    private String status;

    @Column(name = "dt_alarm")
    private LocalDateTime alarmDate;
}
