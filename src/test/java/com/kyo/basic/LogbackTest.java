package com.kyo.basic;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class LogbackTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    void test() {
        logger.debug("[DEBUG]");
        logger.info("[INFO]");
        logger.warn("[WARN]");
        logger.error("[ERROR]");
    }

}
