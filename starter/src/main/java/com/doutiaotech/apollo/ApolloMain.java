package com.doutiaotech.apollo;

import com.doutiaotech.apollo.syncer.ApiSycnerAutoConfiguration;
import com.doutiaotech.apollo.web.WebConfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({
        ApiSycnerAutoConfiguration.class,
        WebConfig.class
})
public class ApolloMain {

    public static void main(String[] args) {
        SpringApplication.run(ApolloMain.class, args);
    }

}
