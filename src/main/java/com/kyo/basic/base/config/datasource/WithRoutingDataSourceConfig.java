package com.kyo.basic.base.config.datasource;

import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Profile({"!local"})
@Slf4j
@Configuration
@RequiredArgsConstructor
public class WithRoutingDataSourceConfig {

	@Value("${spring.datasource.username}")
	private String username;
	@Value("${spring.datasource.password}")
	private String password;
	@Value("${spring.datasource.sample-write.url}")
	private String writeUrl;
	@Value("${spring.datasource.sample-read.url}")
	private String readUrl;

	@Bean(name = "writeSampleDataSource")
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource.sample-write")
	public DataSource writeSampleDataSource() {
		return DataSourceBuilder.create().type(HikariDataSource.class)
				.url(writeUrl)
				.username(username)
				.password(password)
				.build();
	}

	@Bean(name = "readSampleDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.sample-read")
	public DataSource readSampleDataSource() {
		return DataSourceBuilder.create().type(HikariDataSource.class)
				.url(readUrl)
				.username(username)
				.password(password)
				.build();
	}

	@Bean(name = "routingDataSource")
	public DataSource routingDataSource(
			@Qualifier("writeSampleDataSource") DataSource writeSampleDataSource,
			@Qualifier("readSampleDataSource") DataSource readSampleDataSource) {
		ReplicationRoutingDataSource routingDataSource = new ReplicationRoutingDataSource();

		Map<Object, Object> dataSourceMap = new HashMap<>();
		dataSourceMap.put("write", writeSampleDataSource);
		dataSourceMap.put("read", readSampleDataSource);
		routingDataSource.setTargetDataSources(dataSourceMap);
		routingDataSource.setDefaultTargetDataSource(writeSampleDataSource);

		return routingDataSource;
	}

	@Bean(name = "dataSource")
	public DataSource dataSource(@Qualifier("routingDataSource") DataSource routingDataSource) {
		return new LazyConnectionDataSourceProxy(routingDataSource);
	}

}
