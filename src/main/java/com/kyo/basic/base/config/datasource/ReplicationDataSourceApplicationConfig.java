package com.kyo.basic.base.config.datasource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

@Profile({"!local"})
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.kyo.basic.sample.repository")
public class ReplicationDataSourceApplicationConfig {

	@Bean
	@Primary
	@PersistenceContext(unitName = "replication")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
		@Qualifier("dataSource") DataSource dataSource, EntityManagerFactoryBuilder builder
	) {
		return builder
			.dataSource(dataSource)
			.packages("com.kyo.basic.sample.repository.entity")
			.persistenceUnit("replication")
			.build();
	}

	@Bean
	@Primary
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		transactionManager.setPersistenceUnitName("replication");
		return transactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslationPostProcessor() {
		return new PersistenceExceptionTranslationPostProcessor();
	}
}
