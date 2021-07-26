package com.doutiaotech.apollo.syncer;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableScheduling
public class ApiSycnerAutoConfiguration {

    @Bean(destroyMethod = "shutdown")
    @ConfigurationProperties("syncer.trade")
    public ThreadPoolTaskExecutor tradeSyncExecutor() {
        return new ThreadPoolTaskExecutor();
    }

}
