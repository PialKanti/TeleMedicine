package com.codecraftershub.telemedicine.entities.user;

import com.codecraftershub.telemedicine.entities.common.AuditableEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "doctors")
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
public class Doctor extends AuditableEntity {
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String speciality;
    @Column(name = "registration_no", nullable = false)
    private String registrationNumber;
    @Column(name = "nid", nullable = false)
    private String nidNumber;
    private double fee;
    @Column(name = "approved")
    private boolean isApproved;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
