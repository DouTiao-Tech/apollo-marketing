package com.doutiaotech.apollo.infrastructure.mysql;

import com.doutiaotech.apollo.infrastructure.mysql.BaseDaoTest.TestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@TestPropertySource("classpath:application-unit-test.properties")
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
