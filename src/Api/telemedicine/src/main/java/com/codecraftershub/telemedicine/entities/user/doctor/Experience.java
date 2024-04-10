package com.codecraftershub.telemedicine.entities.user.doctor;

import com.codecraftershub.telemedicine.entities.common.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Entity
@Table(name = "experiences")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Experience extends AuditableEntity {
    @Column(nullable = false)
    private String designation;
    @Column(nullable = false)
    private String hospital;
    @Column(nullable = false)
    private LocalDate startDate;
    private LocalDate endDate;
    @Column(name = "current_role")
    private boolean isCurrentRole;
}
