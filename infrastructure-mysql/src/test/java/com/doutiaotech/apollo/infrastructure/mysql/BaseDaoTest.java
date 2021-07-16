package com.doutiaotech.apollo.infrastructure.mysql;

import ch.vorburger.exec.ManagedProcessException;
import ch.vorburger.mariadb4j.springframework.MariaDB4jSpringService;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = MysqlAutoConfiguration.class,
        properties = {
                "mysql.datasource.jdbcUrl=jdbc:mysql://localhost:33306/apollo",
                "mysql.datasource.driverClassName=com.mysql.cj.jdbc.Driver",
                "spring.main.banner-mode=off"
        }
)
@Transactional
@EnableConfigurationProperties(DataSourceProperties.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public abstract class BaseDaoTest {

    static MariaDB4jSpringService DB;

    @BeforeClass
    public static void init() throws ManagedProcessException {
        DB = new MariaDB4jSpringService();
        // 这里用33306防止测试的时候和docker的mysql端口冲突
        DB.setDefaultPort(33306);
        DB.start();
        DB.getDB().source("schema.sql");
        DB.getDB().source("data.sql");
    }

    @AfterClass
    public static void cleanup() {
        if (DB != null) DB.stop();
    }

}
