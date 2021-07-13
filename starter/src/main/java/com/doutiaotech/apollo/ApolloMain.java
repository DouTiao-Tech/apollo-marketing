package com.doutiaotech.apollo;

import com.doutiaotech.apollo.external.dy.DyApiAutoConfiguration;
import com.doutiaotech.apollo.syncer.ApiSycnerConfig;
import com.doutiaotech.apollo.web.WebConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({
        ApiSycnerConfig.class,
        WebConfig.class
})
public class ApolloMain {

    public static void main(String[] args) {
        SpringApplication.run(ApolloMain.class, args);
    }

}
