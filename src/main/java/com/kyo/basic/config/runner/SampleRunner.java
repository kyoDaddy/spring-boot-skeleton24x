package com.kyo.basic.config.runner;

import com.kyo.basic.config.properties.DaemonProp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SampleRunner implements ApplicationRunner {
    @Value("${server.port}")
    private String port;

    @Autowired
    private DaemonProp daemonProp;

    //@Autowired
    //private String kyo;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("on port (runner) : " + port);
        System.out.println("on port (runner) : " + daemonProp.getNickName());
    }
}
