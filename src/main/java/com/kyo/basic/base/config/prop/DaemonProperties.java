package com.kyo.basic.base.config.prop;


import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @ConfigurationProperties
 *      - 자동 설정 클래스 내의 스프링 빈을 property 파일을 통해 재정의 하고자 할 때는 이 어노테이션을 사용한다.
 *      - build.gradle 내 spring-boot-configuration-processor dependency 추가 필요
 */
@Slf4j
@Getter @Setter
@Component
@EnableConfigurationProperties
@ConfigurationProperties("daemon")
public class DaemonProperties {

    private String nickName;

    @PostConstruct
    private void showProperties() {
        log.info("nickName: {}", nickName);
    }

}

