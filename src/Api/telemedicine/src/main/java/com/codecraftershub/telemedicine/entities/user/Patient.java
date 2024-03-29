package com.codecraftershub.telemedicine.entities.user;

import com.codecraftershub.telemedicine.entities.common.AuditableEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Entity
@Table(name = "patients")
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
public class Patient extends AuditableEntity {
    @Column(name = "dob", nullable = false)
    private LocalDate dateOfBirth;
    @Column(name = "blood_group")
    private String bloodGroup;
    private String gender;
    private String address;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
