package com.doutiaotech.apollo.worker.scheduler;

import com.doutiaotech.apollo.infrastructure.clickhouse.mapper.TradeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MarketingJobScheduler {

    @Autowired
    private TradeMapper tradeMapper;

    @Scheduled(fixedDelay = 1000)
    public void schedule() {
        log.info("do marketing job");
        tradeMapper.findById(1L);
    }

}
