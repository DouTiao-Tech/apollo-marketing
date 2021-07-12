package com.doutiaotech.apollo.infrastructure.mysql;

import com.doutiaotech.apollo.infrastructure.mysql.dao.UserDao;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJdbcAuditing
@EnableJdbcRepositories(jdbcOperationsRef = "mysqlOperations", basePackageClasses = UserDao.class, namedQueriesLocation = "classpath:sql/mysql.properties")
public class MysqlConfig {

    @Bean
    @Primary
    @ConfigurationProperties("mysql.datasource")
    public DataSource mysqlDatasource() {
        return new HikariDataSource();
    }

    @Bean
    @Primary
    public NamedParameterJdbcOperations mysqlOperations() {
        return new NamedParameterJdbcTemplate(mysqlDatasource());
    }

    @Bean
    @Primary
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(mysqlDatasource());
    }

}
