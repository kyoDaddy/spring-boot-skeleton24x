package com.kyo.basic.base.config.logback;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SampleAppender extends AppenderBase<ILoggingEvent> {

    @Override
    protected void append(ILoggingEvent event) {

        Level level = event.getLevel();
        if (level.isGreaterOrEqual(Level.ERROR)) {
            log.error("SampleAppender Error!!");
        }

    }

}
