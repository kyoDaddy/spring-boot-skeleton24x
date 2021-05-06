package com.kyo.basic.config.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class ShutDownCustomConfig {

    protected final Logger log = LoggerFactory.getLogger(ShutDownCustomConfig.class);

    @PostConstruct
    public void init() {
        log.info("embedded tomcat start.... hi~");
    }

    @PreDestroy
    public void close() {
        log.info("embedded tomcat shut down.... bye~");
    }

}
