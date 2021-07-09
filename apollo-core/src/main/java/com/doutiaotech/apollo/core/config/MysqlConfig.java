package com.doutiaotech.apollo.core.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class MysqlConfig {

    @Bean
    @Primary
    @ConfigurationProperties("mysql.datasource")
    public DataSource clickHouseDataSource() {
        return new HikariDataSource();
    }

}
