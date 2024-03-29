package com.codecraftershub.telemedicine.configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "jwt.config")
public class JwtConfig {
    private String encryptionKey;
    private long tokenExpiration;
}
