package com.doutiaotech.apollo.infrastructure.mysql;

import javax.sql.DataSource;

import com.doutiaotech.apollo.infrastructure.mysql.dao.UserDao;
import com.zaxxer.hikari.HikariDataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.relational.core.dialect.Dialect;
import org.springframework.data.relational.core.dialect.MySqlDialect;
import org.springframework.data.relational.core.sql.IdentifierProcessing;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJdbcAuditing
@EnableJdbcRepositories(jdbcOperationsRef = "mysqlOperations", basePackageClasses = UserDao.class, namedQueriesLocation = "classpath:sql/mysql.properties")
public class MysqlConfig extends AbstractJdbcConfiguration {

    @Bean
    @Primary
    @ConfigurationProperties("mysql.datasource")
    public DataSource dataSource() {
        return new HikariDataSource();
    }

    @Bean
    @Primary
    public NamedParameterJdbcOperations mysqlOperations() {
        return new NamedParameterJdbcTemplate(dataSource());
    }

    @Bean
    @Primary
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    @Override
    public Dialect jdbcDialect(NamedParameterJdbcOperations mysqlOperations) {
        return new MySqlDialect(IdentifierProcessing.ANSI);
    }
}
