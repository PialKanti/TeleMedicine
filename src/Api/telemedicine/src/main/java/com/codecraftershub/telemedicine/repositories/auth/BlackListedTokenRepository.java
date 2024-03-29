package com.codecraftershub.telemedicine.repositories.auth;

import com.codecraftershub.telemedicine.entities.auth.BlackListedToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BlackListedTokenRepository extends JpaRepository<BlackListedToken, Long> {
    Optional<BlackListedToken> findByToken(String token);
}
