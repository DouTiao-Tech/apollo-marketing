package com.doutiaotech.apollo;

import com.doutiaotech.apollo.external.dy.ApiConfig;
import com.doutiaotech.apollo.infrastructure.clickhouse.ClickHouseConfig;
import com.doutiaotech.apollo.infrastructure.mysql.MysqlConfig;
import com.doutiaotech.apollo.syncer.ApiSycnerConfig;
import com.doutiaotech.apollo.web.WebConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({
        MysqlConfig.class,
        ClickHouseConfig.class,
        ApiConfig.class,
        ApiSycnerConfig.class,
        WebConfig.class
})
public class ApolloMain {

    public static void main(String[] args) {
        SpringApplication.run(ApolloMain.class, args);
    }

}
