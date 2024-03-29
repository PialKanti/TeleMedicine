package com.codecraftershub.telemedicine.entities.lookup;

import com.codecraftershub.telemedicine.entities.common.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "lookups")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Lookup extends AuditableEntity {
    @Column(nullable = false)
    private String category;
    @Column(name = "lookup_key", nullable = false)
    private String key;
    @Column(nullable = false)
    private String value;
}
