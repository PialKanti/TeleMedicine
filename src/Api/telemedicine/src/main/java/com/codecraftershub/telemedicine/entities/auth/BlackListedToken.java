package com.codecraftershub.telemedicine.entities.auth;

import com.codecraftershub.telemedicine.entities.common.NonAuditableEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@Table(name = "blacklist_tokens")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class BlackListedToken extends NonAuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "token")
    private String token;
    @Column(name = "expired_at")
    private LocalDateTime expiryDateTime;
}
