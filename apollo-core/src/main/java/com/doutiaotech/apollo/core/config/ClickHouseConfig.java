package com.doutiaotech.apollo.core.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class ClickHouseConfig {

    @Bean
    @ConfigurationProperties("clickhouse.datasource")
    public DataSource clickHouseDataSource() {
        return new HikariDataSource();
    }

}
