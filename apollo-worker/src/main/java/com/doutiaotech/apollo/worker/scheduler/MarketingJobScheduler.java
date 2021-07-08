package com.doutiaotech.apollo.worker.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MarketingJobScheduler {

    @Scheduled(fixedDelay = 1000)
    public void schedule() {
        log.info("do marketing job");
    }

}
