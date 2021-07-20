package com.doutiaotech.apollo.infrastructure.clickhouse;

import com.doutiaotech.apollo.infrastructure.clickhouse.BaseDaoTest.TestConfig;

import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { TestConfig.class }, properties = { "embedded.containers.forceShutdown=true",
        "embedded.clickhouse.dockerImage=yandex/clickhouse-server:21.7.4-alpine", "embedded.containers.reuseContainer=true",
        // "embedded.clickhouse.init-script-path=schema.sql",
        "clickhouse.datasource.jdbcUrl=jdbc:clickhouse://${embedded.clickhouse.host}:${embedded.clickhouse.port}/${embedded.clickhouse.schema}",
        "clickhouse.datasource.driverClassName=ru.yandex.clickhouse.ClickHouseDriver",
        "clickhouse.datasource.username=${embedded.clickhouse.user}",
        "clickhouse.datasource.password=${embedded.clickhouse.password}", "spring.main.banner-mode=off" })
public abstract class BaseDaoTest {

    @Configuration
    @EnableAutoConfiguration
    static class TestConfig {

    }
}
