package com.kyo.basic.config.listner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

/**
 * ApplicationArguments
 *      - 애플리케이션 아규먼트란 Program arguments에서 --로 들어오는 아규먼트를 의미
 *      - JVM arguments는 -D로 들어오는 아규먼트를 의미 (application argument가 아님)
 */
@Component
public class AppArgumentsListner {
    public AppArgumentsListner(ApplicationArguments arguments) {
        System.out.println("VM arguments (Debug) : " + arguments.containsOption("Debug"));
        System.out.println("Program arguments (kyo) : " + arguments.containsOption("kyo"));
    }
}
