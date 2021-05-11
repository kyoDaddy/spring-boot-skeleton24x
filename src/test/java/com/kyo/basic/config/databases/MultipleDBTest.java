package com.kyo.basic.config.databases;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class MultipleDBTest {

    String bookUrl ="jdbc:mariadb://localhost:49168/book?user=kyo&password=woduf8948!";
    String testUrl ="jdbc:mariadb://localhost:49168/test?user=kyo&password=woduf8948!";

    @Test
    public void connBook() {
        try {

            Class.forName("org.mariadb.jdbc.Driver");
            Connection conn = DriverManager.getConnection(bookUrl);
            System.out.println("### book connection : " + conn + " - 연결 성공 ####");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void connTest() {
        try {

            Class.forName("org.mariadb.jdbc.Driver");
            Connection conn = DriverManager.getConnection(testUrl);
            System.out.println("### test connection : " + conn + " - 연결 성공 ####");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
