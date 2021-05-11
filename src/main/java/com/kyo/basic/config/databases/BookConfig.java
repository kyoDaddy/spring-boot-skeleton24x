package com.kyo.basic.config.databases;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
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
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@EnableTransactionManagement
public class BookConfig {

    @Bean
    @Primary
    @ConfigurationProperties("datasource.book")
    public HikariConfig hikariConfig() {
        return new HikariConfig();
    }

    @Bean(name="book-ds")
    public DataSource bookDataSource() {
        HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig());
        return hikariDataSource;
    }

    @Primary
    @Bean(name = "book-ssf")
    public SqlSessionFactory bookSqlSessionFactory(@Qualifier("book-ds") DataSource bookDataSource,
                                                    ApplicationContext applicationContext) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(bookDataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage("com.kyo.basic.process.vo");
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mybatis/book/**.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Primary
    @Bean(name = "book-sst")
    public SqlSessionTemplate bookSqlSessionTemplate(@Qualifier("book-ssf") SqlSessionFactory bookSqlSessionFactory) {
        return new SqlSessionTemplate(bookSqlSessionFactory);
    }

    @Bean(name="book-tm")
    @Autowired
    @Primary
    DataSourceTransactionManager bookTransactionManager(@Qualifier("book-ds") DataSource datasource) {
        DataSourceTransactionManager txm  = new DataSourceTransactionManager(datasource);
        return txm;
    }

}
