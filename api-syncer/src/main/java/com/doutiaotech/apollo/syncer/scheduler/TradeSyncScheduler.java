package com.doutiaotech.apollo.syncer.scheduler;

import com.doutiaotech.apollo.external.dy.api.OrderApi;
import com.doutiaotech.apollo.infrastructure.mysql.dao.SyncItemDao;
import com.doutiaotech.apollo.infrastructure.mysql.model.SyncItem;
import com.doutiaotech.apollo.infrastructure.mysql.model.SyncerType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class TradeSyncScheduler {

    @Autowired
    private SyncItemDao syncItemDao;

    @Autowired
    private OrderApi orderApi;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Scheduled(fixedDelay = 1000)
    public void schedule() {
        List<SyncItem> syncItems = syncItemDao.findByTypeAndStop(SyncerType.FETCH_TRADE, false);
        log.info("fetch trade and send to kafka");
        kafkaTemplate.send("trade", "json data");
    }

    class TradeSyncTask implements Runnable {
        SyncItem syncItem;

        TradeSyncTask(SyncItem syncItem) {
            this.syncItem = syncItem;
        }

        @Override
        public void run() {

        }
    }

}
