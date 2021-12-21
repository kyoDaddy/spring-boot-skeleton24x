package com.kyo.basic.base.config.datasource.local;

import com.kyo.basic.base.utils.RandomByteUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * h2 inmemory db
 */
@Profile("local")
@Component
@Slf4j
public class EmbeddedH2Config implements ApplicationRunner {

    final DataSource source;
    final JdbcTemplate jdbcTemplate;

    public EmbeddedH2Config(DataSource source, JdbcTemplate jdbcTemplate) {
        this.source = source;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        try(Connection connection = source.getConnection()) {
            log.info("h2 inmemory db url --> {}, username --> {} ", connection.getMetaData().getURL(), connection.getMetaData().getUserName());

            // /h2-console uri, JDBC URL (jdbc:h2:mem:testdb), User Name (sa)
            Statement statement = connection.createStatement();

            //jdbcTemplate.execute("INSERT INTO AUTH_INFO VALUES (1, 'kyo', '23375')");
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO AUTH_INFO (id, mapping_id, unique_key, enc_key, enc_key_updated_at, created_at) VALUES ( ");
            sql.append("1, ");
            sql.append("'" + UUID.randomUUID().toString() + "', ");
            sql.append("'0000000000000001', ");
            sql.append("'" + RandomByteUtils.makeKey() + "', ");
            sql.append("'" + LocalDateTime.now() + "', ");
            sql.append("'" + LocalDateTime.now() + "' )");

            jdbcTemplate.execute(sql.toString());
        }

    }

}
