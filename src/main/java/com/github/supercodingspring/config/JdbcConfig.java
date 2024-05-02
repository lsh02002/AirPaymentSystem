package com.github.supercodingspring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class JdbcConfig {

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUsername("root");
        //암호는 따로 설정하였습니다.
        dataSource.setPassword("******");
        dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
        dataSource.setUrl("jdbc:mariadb://127.0.0.1:3306/chapter_97");
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() { return new JdbcTemplate(dataSource()); }

    @Bean(name = "tm")
    public PlatformTransactionManager transactionManager() { return new DataSourceTransactionManager(dataSource()); }
}
