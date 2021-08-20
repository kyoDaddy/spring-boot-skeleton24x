package com.kyo.basic.config.properties;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import javax.annotation.PostConstruct;
import java.time.Duration;

/**
 * @ConfigurationProperties
 *      - 자동 설정 클래스 내의 스프링 빈을 property 파일을 통해 재정의 하고자 할 때는 이 어노테이션을 사용한다.
 *      - build.gradle 내 spring-boot-configuration-processor dependency 추가 필요
*/
@ConfigurationProperties("daemon")
@Getter
@Slf4j
public class DaemonProp {

    private String nickName;
    private String rootUri;

    @ConstructorBinding
    public DaemonProp(String nickName, String rootUri) {
        this.nickName = nickName;
        this.rootUri = rootUri;
    }

    @PostConstruct
    public void setUp() {
        log.info("nickName --> {}", nickName);
        log.info("rootUri --> {}", rootUri);
    }

}
