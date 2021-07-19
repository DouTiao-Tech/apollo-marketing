package com.doutiaotech.apollo.infrastructure.mysql;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

import com.doutiaotech.apollo.infrastructure.mysql.BaseDaoTest.TestConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class}, properties = {"embedded.containers.forceShutdown=true",
        "embedded.mysql.dockerImage=mysql:5.6.50", "embedded.containers.reuseContainer=true",
        "embedded.mysql.init-script-path=schema.sql",
        "mysql.datasource.jdbcUrl=jdbc:mysql://${embedded.mysql.host}:${embedded.mysql.port}/${embedded.mysql.schema}?useSSL=false",
        "mysql.datasource.driverClassName=com.mysql.cj.jdbc.Driver", "mysql.datasource.username=${embedded.mysql.user}",
        "mysql.datasource.password=${embedded.mysql.password}", "spring.main.banner-mode=off", "logging.level.root=INFO"})
@Transactional
public abstract class BaseDaoTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    public void shouldConnectToMySQL() throws Exception {
        assertThat(jdbcTemplate.queryForObject("select version()", String.class), containsString("5.6.50"));
    }

    @Configuration
    @EnableAutoConfiguration
    static class TestConfig {

    }

}
