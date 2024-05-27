package br.com.fiap.public_security.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ps_public_servants")
public class PublicServants {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "PS_PUBLIC_SERVANTS_SEQ"
    )
    @SequenceGenerator(
            name = "PS_PUBLIC_SERVANTS_SEQ",
            sequenceName = "PS_PUBLIC_SERVANTS_SEQ",
            allocationSize = 1,
            initialValue = 1
    )
    @Column(name = "public_servant_id")
    private Long publicServantId;
    private String department;




}
