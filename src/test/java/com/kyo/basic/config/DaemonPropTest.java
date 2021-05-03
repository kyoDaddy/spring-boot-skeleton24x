package com.kyo.basic.config;

import com.kyo.basic.config.properties.DaemonProp;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
/** @ActiveProfiles({"dev"}) // spring.config.activate.on-profile: daemon-dev 활성화
 * 주의사항!! spring.profiles 속성이 제거되면 스프링 부트 2.4 부터는 YAML 내에 있는 문서구분을 무시하고 마지막 문서에 있는 속성으로 덮어쓴다.
 * */
public class DaemonPropTest {
    @Autowired
    DaemonProp daemonProp;

    @Test
    @DisplayName("포로파일을 선언하지 않았다!")
    void test() {
        assertThat(daemonProp.getRootUri()).isEqualTo("http://dev.naver.com");
        assertThat(daemonProp.getNickName()).isEqualTo("Developer dev");
    }

}
