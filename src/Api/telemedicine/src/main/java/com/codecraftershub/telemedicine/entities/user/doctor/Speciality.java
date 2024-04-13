package com.codecraftershub.telemedicine.entities.user.doctor;

import com.codecraftershub.telemedicine.entities.common.NonAuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "specialities")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Speciality extends NonAuditableEntity {
    @Column(nullable = false)
    private String name;
    private String description;
    @Column(name = "active", nullable = false)
    private boolean isActive;
}
