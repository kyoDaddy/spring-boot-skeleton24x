package com.kyo.basic.config.properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("dev")
@Configuration
public class DevConfig {
    @Bean
    public String kyo() {
        return "kyo dev!";
    }
}
