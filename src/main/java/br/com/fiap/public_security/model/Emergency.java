package br.com.fiap.public_security.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "ps_emergencies")
@Getter
@Setter
public class Emergency {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "PS_EMERGENCIES_SEQ"
    )
    @SequenceGenerator(
            name = "PS_EMERGENCIES_SEQ",
            sequenceName = "PS_EMERGENCIES_SEQ",
            allocationSize = 1
    )
    @Column(name = "emergency_id")
    private Long emergencyId;
    @Column(name = "requester_email")
    private String requesterEmail;
    @Column(name = "dt_request")
    private LocalDateTime requestDate;
    private String address;
    private String type;
    private String title;
    private String description;
    private String status;

}
