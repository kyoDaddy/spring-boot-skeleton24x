package com.kyo.basic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;

/**
 * SpringBootApplication
 *      EnableAutoConfiguration, ComponentScan 이 안에 들어있음
 *
 * Bean 등록 단계
 *      ComponentScan 에서 Bean 스캔해 등록
 *      등록된 정보를 바탕으로 jar 파일 내의 META-INF 중 spring.factories 안의 auto-configuration 클래스 목록들을 참조해서 자동 설정을 시작
 *        ConditionalOnMissingBean : 기존 그런 Bean이 없으면 자동설정으로 등록
 */

@EnableCaching
@ServletComponentScan
@SpringBootApplication
public class BasicApplication {
    /* 최상위 패키지부터 ComponentScan이 시작되어 차례대로 Bean에 등록이 되기 때문에 최상위 패키지에 위치시키는게 좋다 */
    public static void main(String[] args) {
        SpringApplication.run(BasicApplication.class, args);

        /**
         * SpringApplication의 여러 기능들을 입맛에 맞게 사용하고자 할 때는 아래와 같이 Spring Application 인스턴스를 생성해 실행하는 편이 좋다.
         *      SpringApplication app = new SpringApplication(BasicApplication.class);
         *
         *      // Spring Boot 백그라운드 jar 중지 (kill pid)
         *      // 시작 : nohup java -jar demo.jar &
         *      // 중지 : kill -9 `cat boot.pid`
         *      app.addListeners(new ApplicationPidFileWriter());
         *
         *      // Application Context 생성 이전의 이벤트 리스너 등록
         *      app.addListeners(new AppListner());
         *
         *      // 배너 등록
         *      app.setBanner( (environment, sourceClass, out) -> {
         *          out.println("============");
         *          out.println("kyo bannner :)");
         *          out.println("============");
         *      });
         *
         *      // WebApplicationType 적용 순서 SERVLET -> REACTIVE -> NONE 순서로 적용
         *      // Spring MVC가 있다면 기본적으로 SERVLET으로 작동
         *      app.setWebApplicationType(WebApplicationType.SERVLET);
         *      // 둘다없다
         *      app.setWebApplicationType(WebApplicationType.REACTIVE);
         *      // Spring WebFlux가 있다면 기본적으로 REACTIVE으로 작동
         *      app.setWebApplicationType(WebApplicationType.NONE);
         *
         *      app.run(args);
        */
    }

    /*
    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            System.out.println("Let's inspect the beans provided by Spring Boot:");
            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for(String beanName : beanNames) {
                System.out.println(beanName);
            }
        };
    }
    */
}
