package com.kyo.basic.config.databases;

//xml설정방식 사용 시
//@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.logging.Logger;

//java설정방식 사용 시
//@ContextConfiguration(classes = {RootConfig.class})
@ExtendWith(SpringExtension.class)
@ActiveProfiles("local")
@SpringBootTest
public class HikariDataSourceTest {

    private static final Logger log = Logger.getLogger(String.valueOf(HikariDataSourceTest.class));
    @Autowired
    private DataSource dataSource;
    @Test
    public void hikariDataSourceTest() {
        try {
            Connection con = dataSource.getConnection();
            log.info("'Connection객체 : " + con + "'");
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }

}
