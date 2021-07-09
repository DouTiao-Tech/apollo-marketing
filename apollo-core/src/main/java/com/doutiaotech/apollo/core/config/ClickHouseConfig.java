package com.doutiaotech.apollo.core.config;

import javax.sql.DataSource;

import com.doutiaotech.apollo.core.dao.clickhouse.TradeDao;
import com.zaxxer.hikari.HikariDataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
@EnableJdbcRepositories(jdbcOperationsRef = "clickHouseOperations", basePackageClasses = TradeDao.class, namedQueriesLocation = "classpath:sql/clickhouse.properties")
public class ClickHouseConfig {

    @Bean
    @ConfigurationProperties("clickhouse.datasource")
    public DataSource clickHouseDataSource() {
        return new HikariDataSource();
    }

    @Bean
    public NamedParameterJdbcOperations clickHouseOperations() {
        return new NamedParameterJdbcTemplate(clickHouseDataSource());
    }

}
