package com.kyo.basic.config.base;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @Profile
 *      - Profile이란 스프링에서 특정 Profile에서만 특정한 빈을 등록하고 싶다거나, 애플리케이션 동작을 특정 Profile에서 설정을 다르게 하고 싶을 때 사용하는 기능
 *      - 애노테이션은 보통 @Configuration @Component와 함께 쓰인다.
 */
@Profile("live")
@Configuration
public class LiveConfig {
    @Bean
    public String kyo() {
        return "kyo live";
    }
}
