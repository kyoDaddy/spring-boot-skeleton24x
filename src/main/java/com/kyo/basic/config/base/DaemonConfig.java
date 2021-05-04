package com.kyo.basic.config.base;

import com.kyo.basic.config.properties.DaemonProp;
import com.kyo.basic.process.vo.base.DaemonVo;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * EnableConfigurationPropertes
 *      - 이 어노테이션을 통해 DaemonConfiguration 클래스를 프로퍼티 파일(DaemonProperties) 정보를 담고 있는 클래스로 자동 등록하게 해준다.
 *      - 타입 세이프 프로퍼티
 */
@Configuration
@EnableConfigurationProperties(DaemonProp.class)
public class DaemonConfig {

    /**
     * ConditionalOnMissingBean
     * * 스프링 부트 프로젝트 상에서 같은 이름의 스프링 빈이 있을 때에는 그 스프링 빈을 사용하고, 만약 없다면 자동 등록한 빈을 쓰게끔 유도
     */
    @Bean (name="daemon-config")
    @ConditionalOnMissingBean
    public DaemonVo daemonVo(DaemonProp properties) {
        DaemonVo daemonVo = new DaemonVo();
        daemonVo.setNickName(properties.getNickName());
        daemonVo.setRootUri(properties.getRootUri());
        return daemonVo;
    }

}
