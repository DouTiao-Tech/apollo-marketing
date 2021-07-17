package com.doutiaotech.apollo.syncer.scheduler;

import java.time.LocalDateTime;

import com.doutiaotech.apollo.external.dy.api.OrderApi;
import com.doutiaotech.apollo.external.dy.response.Response;
import com.doutiaotech.apollo.external.dy.response.TradeSearchPage;
import com.doutiaotech.apollo.infrastructure.mysql.model.SyncItem;
import com.doutiaotech.apollo.infrastructure.mysql.model.SyncType;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = KafkaAutoConfiguration.class, properties = "spring.main.banner-mode=off")
public class TradeSyncSchedulerTest {

    private static final SyncType syncType = SyncType.FETCH_TRADE;
    private static final LocalDateTime start = LocalDateTime.of(2020, 1, 1, 1, 1, 1);
    private static final LocalDateTime end = LocalDateTime.of(2020, 1, 1, 1, 1, 1);

    @MockBean
    OrderApi orderApi;

    @Test
    public void testTradeSyncTask() {
        Mockito.when(orderApi.searchList(null)).thenReturn(mockResponse());
        TradeSyncScheduler scheduler = new TradeSyncScheduler();
        scheduler.new TradeSyncTask(mockSyncItem());
        // TODO: test run()
    }

    private Response<TradeSearchPage> mockResponse() {
        return null;
    }

    private SyncItem mockSyncItem() {
        SyncItem syncItem = new SyncItem();
        syncItem.setId(1L);
        syncItem.setShopId(1L);
        syncItem.setType(syncType);
        syncItem.setStart(syncType.toJson(start));
        syncItem.setProgress(syncType.toJson(start));
        syncItem.setEnd(syncType.toJson(end));
        return syncItem;
    }

}
