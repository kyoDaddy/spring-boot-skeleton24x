package com.kyo.basic.config.databases;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class TestConfig {

    @Bean(name="test-ds")
    @ConfigurationProperties(prefix="datasource.test")
    public DataSource testDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "test-ssf")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("book-ds") DataSource testDataSource,
                                                   ApplicationContext applicationContext) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(testDataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage("com.kyo.basic.process.vo");
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mybatis/test/**.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Primary
    @Bean(name = "test-sst")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("test-ssf") SqlSessionFactory testSqlSessionFactory) {
        return new SqlSessionTemplate(testSqlSessionFactory);
    }

    @Bean(name="test-tm")
    @Autowired
    @Primary
    DataSourceTransactionManager testTransactionManager(@Qualifier("test-ds") DataSource datasource) {
        DataSourceTransactionManager txm  = new DataSourceTransactionManager(datasource);
        return txm;
    }


}
