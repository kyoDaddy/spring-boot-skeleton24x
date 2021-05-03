package com.kyo.basic.process.schedulers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DemoScheduler {

    private static Logger logger = LoggerFactory.getLogger(DemoScheduler.class);

    /**
     *      @Scheduled
     *
     *          -> fixedDelay : 작업이 끝난 시점 기준, milliseconds 마다 동작
     *          -> fixedDelayString : fixedDelay와 동일, 속성값만 String으로 입력
     *          -> fixedRate : 작업이 시작한 시점 기준, milliseconds 마다 동작
     *          -> fixedRateString : fixedRate와 동일, 속성값만 String으로 입력
     *          -> initialDelay : 최초 수행 지연 시간, milliseconds 이후에 실행
     *          -> initialDelayString : initialDelay와 동일, 속성값만 String으로 입력
     *          -> zone : 시간대, Oracle Real-Time Collabaration에서 지원하는 시간대 목록
     *                    설정하지 않으면 default는 설치된 서버의 시간대로 설정됨  (예시: "Asia/Seoul")
     *
     *          -> cron : cron 표현식을 사용하여 특정 시간마다 동작 ("초 분 시 일 월 요일" 6필드)
     *
     */
    //@Scheduled(fixedDelay = 1000)
    @Scheduled(cron = "00 00 09 * * *")
    public void alert() {
        logger.info("현재 시간 : {}", new Date());
    }

}
