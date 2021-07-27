package com.doutiaotech.apollo.syncer.scheduler;

import com.doutiaotech.apollo.core.utils.DateTimeUtils;
import com.doutiaotech.apollo.core.utils.JsonUtils;
import com.doutiaotech.apollo.external.dy.api.OrderApi;
import com.doutiaotech.apollo.external.dy.response.Response;
import com.doutiaotech.apollo.external.dy.response.TradeSearchPage;
import com.doutiaotech.apollo.external.dy.response.TradeSearchPage.ShopOrderListBean;
import com.doutiaotech.apollo.infrastructure.mysql.dao.SyncItemDao;
import com.doutiaotech.apollo.infrastructure.mysql.model.SyncItem;
import com.doutiaotech.apollo.infrastructure.mysql.enums.SyncType;
import com.doutiaotech.apollo.syncer.scheduler.TradeSyncScheduler.TradeSyncTask;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.concurrent.ListenableFuture;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.concurrent.ExecutorService;

import static com.doutiaotech.apollo.syncer.scheduler.TradeSyncScheduler.TRADE_TOPIC;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TradeSyncScheduler.class})
public class TradeSyncSchedulerTest {

    private static final SyncType syncType = SyncType.FETCH_TRADE;
    private static final LocalDateTime start = LocalDateTime.of(2020, 1, 1, 1, 1, 1);
    private static final LocalDateTime end = LocalDateTime.of(2020, 3, 1, 1, 1, 1);

    @MockBean
    OrderApi orderApi;

    @MockBean
    KafkaTemplate<String, String> kafkaTemplate;

    @MockBean
    ThreadPoolTaskExecutor tradeSyncExecutor;

    @MockBean
    SyncItemDao syncItemDao;

    @Autowired
    TradeSyncScheduler tradeSyncScheduler;

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    public void testTradeSyncTask() {
        when(syncItemDao.save(any(SyncItem.class))).thenAnswer((invoke) -> invoke.getArgument(0));

        when(orderApi.searchList(any())).thenReturn(
                mockResponse(mockShopOrder(1L, LocalDateTime.of(2020, 1, 15, 1, 1, 1))),
                mockResponse(mockShopOrder(2L, LocalDateTime.of(2020, 2, 1, 1, 1, 1))),
                mockResponse(mockShopOrder(3L, LocalDateTime.of(2020, 2, 15, 1, 1, 1))), mockResponse(null));

        ListenableFuture<SendResult<String, String>> mockResult = mock(ListenableFuture.class);
        doReturn(new SendResult<>(null, null)).when(mockResult).get();
        when(kafkaTemplate.send(eq(TRADE_TOPIC), anyString(), anyString())).thenReturn(mockResult);

        TradeSyncTask task = tradeSyncScheduler.new TradeSyncTask(mockSyncItem());
        task.run();

        InOrder inOrder = inOrder(kafkaTemplate);
        inOrder.verify(kafkaTemplate).send(TRADE_TOPIC, Long.toString(1L),
                JsonUtils.toJson(mockShopOrder(1L, LocalDateTime.of(2020, 1, 15, 1, 1, 1))));
        inOrder.verify(kafkaTemplate).send(TRADE_TOPIC, Long.toString(2L),
                JsonUtils.toJson(mockShopOrder(2L, LocalDateTime.of(2020, 2, 1, 1, 1, 1))));
        inOrder.verify(kafkaTemplate).send(TRADE_TOPIC, Long.toString(3L),
                JsonUtils.toJson(mockShopOrder(3L, LocalDateTime.of(2020, 2, 15, 1, 1, 1))));

        assertEquals(task.syncItem.getProgress(), task.syncItem.getEnd());
    }

    private Response<TradeSearchPage> mockResponse(ShopOrderListBean bean) {
        Response<TradeSearchPage> response = new Response<>();
        TradeSearchPage page = new TradeSearchPage();
        page.setShop_order_list(bean == null ? Collections.emptyList() : Collections.singletonList(bean));
        response.setData(page);
        return response;
    }

    private ShopOrderListBean mockShopOrder(long orderId, LocalDateTime updateTime) {
        ShopOrderListBean bean = new ShopOrderListBean();
        bean.setOrder_id(Long.toString(orderId));
        bean.setUpdate_time(DateTimeUtils.toTimestamp(updateTime));
        return bean;
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
