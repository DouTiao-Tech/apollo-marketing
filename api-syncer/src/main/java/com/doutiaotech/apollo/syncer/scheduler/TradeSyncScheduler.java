package com.doutiaotech.apollo.syncer.scheduler;

import com.doutiaotech.apollo.core.utils.DateTimeUtils;
import com.doutiaotech.apollo.core.utils.JsonUtils;
import com.doutiaotech.apollo.external.dy.api.OrderApi;
import com.doutiaotech.apollo.external.dy.request.Request;
import com.doutiaotech.apollo.external.dy.request.TradeSearch;
import com.doutiaotech.apollo.external.dy.response.Response;
import com.doutiaotech.apollo.external.dy.response.TradeSearchPage;
import com.doutiaotech.apollo.external.dy.response.TradeSearchPage.ShopOrderListBean;
import com.doutiaotech.apollo.infrastructure.mysql.dao.SyncItemDao;
import com.doutiaotech.apollo.infrastructure.mysql.enums.SyncType;
import com.doutiaotech.apollo.infrastructure.mysql.model.SyncItem;
import lombok.extern.slf4j.Slf4j;
import org.jooq.lambda.Unchecked;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Slf4j
@Component
public class TradeSyncScheduler extends BaseSyncScheduler {

    public static final String TRADE_TOPIC = "trade";

    @Autowired
    private SyncItemDao syncItemDao;

    @Autowired
    private OrderApi orderApi;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ThreadPoolTaskExecutor tradeSyncExecutor;

    @Override
    protected List<SyncItem> findSyncItem() {
        return syncItemDao.findByTypeAndStop(SyncType.FETCH_TRADE, false);
    }

    @Override
    protected Future<?> submitTask(SyncItem syncItem) {
        // syncItem.setEnd();
        return tradeSyncExecutor.submit(new TradeSyncTask(syncItem));
    }

    class TradeSyncTask implements Runnable {

        SyncItem syncItem;
        long updateTimeStart;
        long updateTimeEnd;

        TradeSyncTask(SyncItem syncItem) {
            this.syncItem = syncItem;
        }

        @Override
        public void run() {
            LocalDateTime progress = syncItem.resolveProgress();
            LocalDateTime end = syncItem.resolveEnd();
            updateTimeStart = DateTimeUtils.toTimestamp(progress);
            updateTimeEnd = DateTimeUtils.toTimestamp(end);
            log.info("start sync trade for user#{} between {} and {}", syncItem.getShopId(), progress, end);
            try {
                long sizeSum = 0;
                while (!syncItem.isFinish()) {
                    Request<TradeSearch> request = buildRequest(updateTimeStart, updateTimeEnd);
                    Response<TradeSearchPage> response = orderApi.searchList(request);
                    sizeSum += processResponse(response);
                }
                log.info("sync {} trade to kafka for user#{} between {} and {}", sizeSum, syncItem.getShopId(),
                        DateTimeUtils.longToDateTime(updateTimeStart), DateTimeUtils.longToDateTime(updateTimeEnd));
            } catch (Exception e) {
                log.error("sync trade error, syncItem:" + syncItem, e);
            }
        }

        @Transactional
        long processResponse(Response<TradeSearchPage> response) {
            List<ShopOrderListBean> shop_order_list = response.getData().getShop_order_list();
            int size = shop_order_list.size();
            shop_order_list.stream()
                    .map(order -> kafkaTemplate.send(TRADE_TOPIC, order.getOrder_id(), JsonUtils.toJson(order)))
                    .collect(Collectors.toList()).forEach(Unchecked.consumer(Future::get));
            updateTimeStart = size == 0 ? updateTimeEnd : nextUpdateTimeStart(shop_order_list);
            syncItem.updateProgress(DateTimeUtils.longToDateTime(updateTimeStart));
            syncItem = syncItemDao.save(syncItem);
            return size;
        }

        long nextUpdateTimeStart(List<ShopOrderListBean> shop_order_list) {
            return shop_order_list.stream()
                    .mapToLong(ShopOrderListBean::getUpdate_time).max()
                    .orElseThrow(AssertionError::new);
        }

        Request<TradeSearch> buildRequest(long updateTimeStart, long updateTimeEnd) {
            Request<TradeSearch> request = new Request<>();
            // TODO: common request params
            TradeSearch search = new TradeSearch();
            search.setUpdate_time_start(updateTimeStart);
            search.setUpdate_time_end(updateTimeEnd);
            search.setOrder_by("update_time");
            search.setOrder_asc(true);
            search.setSize(syncItem.getStep());
            return request;
        }
    }

}
