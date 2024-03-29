package com.codecraftershub.telemedicine.entities.user;

import com.codecraftershub.telemedicine.entities.common.NonAuditableEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Table(name = "permissions")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Permission extends NonAuditableEntity {
    @Column(name = "permission_key", nullable = false, length = 100)
    private String key;
    @Column(nullable = false, length = 100)
    private String name;
    @ManyToMany(mappedBy = "permissions")
    @JsonIgnore
    private List<Role> roles;
}
