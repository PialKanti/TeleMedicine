package com.codecraftershub.telemedicine.entities.user.doctor;

import com.codecraftershub.telemedicine.entities.common.AuditableEntity;
import com.codecraftershub.telemedicine.entities.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

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
    @ManyToMany
    @JoinTable(name = "doctor_specialities", joinColumns = @JoinColumn(name = "doctor_id"), inverseJoinColumns = @JoinColumn(name = "speciality_id"))
    private List<Speciality> speciality;
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
    @OneToMany
    @JoinTable(name = "doctor_experiences", joinColumns = @JoinColumn(name = "doctor_id"), inverseJoinColumns = @JoinColumn(name = "experience_id"))
    private List<Experience> experiences;
}
