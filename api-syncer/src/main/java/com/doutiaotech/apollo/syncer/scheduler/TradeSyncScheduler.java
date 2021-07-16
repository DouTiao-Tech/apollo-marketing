package com.doutiaotech.apollo.syncer.scheduler;

import com.doutiaotech.apollo.core.utils.DateTimeUtils;
import com.doutiaotech.apollo.core.utils.JsonUtils;
import com.doutiaotech.apollo.external.dy.api.OrderApi;
import com.doutiaotech.apollo.external.dy.request.Request;
import com.doutiaotech.apollo.external.dy.request.TradeSearch;
import com.doutiaotech.apollo.external.dy.response.Response;
import com.doutiaotech.apollo.external.dy.response.TradeSearchPage;
import com.doutiaotech.apollo.infrastructure.mysql.model.SyncItem;
import com.doutiaotech.apollo.infrastructure.mysql.model.SyncType;
import lombok.extern.slf4j.Slf4j;
import org.jooq.lambda.Unchecked;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Slf4j
@Component
public class TradeSyncScheduler extends BaseSyncScheduler {

    public static final String TRADE_TOPIC = "trade";

    @Autowired
    private OrderApi orderApi;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ExecutorService tradeSyncExecutor;

    @Override
    protected SyncType supportType() {
        return SyncType.FETCH_TRADE;
    }

    @Override
    protected Future<?> submitTask(SyncItem syncItem) {
        return tradeSyncExecutor.submit(new TradeSyncTask(syncItem));
    }

    class TradeSyncTask implements Runnable {

        private SyncItem syncItem;

        TradeSyncTask(SyncItem syncItem) {
            this.syncItem = syncItem;
        }

        @Override
        public void run() {
            LocalDateTime progress = syncItem.resolveProgress();
            LocalDateTime end = syncItem.resolveEnd();
            log.info("start sync trade for user#{} between {} and {}", syncItem.getShopId(), progress, end);
            long updateTimeStart = DateTimeUtils.toTimestamp(progress);
            long updateTimeEnd = DateTimeUtils.toTimestamp(end);
            try {
                while (!syncItem.isFinish()) {
                    Request<TradeSearch> request = buildRequest(updateTimeStart, updateTimeEnd);
                    Response<TradeSearchPage> response = orderApi.searchList(request);
                    progressResponse(response);
                    int size = response.getData().getShop_order_list().size();
                    if (log.isDebugEnabled()) {
                        log.debug("sync {} trade to kafka for user#{} between {} and {}",
                                size, syncItem.getShopId(),
                                DateTimeUtils.longToDateTime(updateTimeStart),
                                DateTimeUtils.longToDateTime(updateTimeEnd)
                        );
                    }
                    updateTimeStart = size == 0 ? updateTimeEnd : nextUpdateTimeStart(response);
                    syncItem.updateProgress(DateTimeUtils.longToDateTime(updateTimeEnd));
                    syncItemDao.save(syncItem);
                }
            } catch (Exception e) {
                log.error("sync trade error, syncItem:" + syncItem, e);
            }
        }

        private long nextUpdateTimeStart(Response<TradeSearchPage> response) {
            return response.getData().getShop_order_list().stream()
                    .mapToLong(TradeSearchPage.ShopOrderListBean::getUpdate_time)
                    .max().orElseThrow(AssertionError::new);
        }

        private void progressResponse(Response<TradeSearchPage> response) {
            TradeSearchPage data = response.getData();
            data.getShop_order_list().stream().map(order -> kafkaTemplate.send(TRADE_TOPIC, JsonUtils.toJson(order)))
                    .collect(Collectors.toList())
                    .forEach(Unchecked.consumer(Future::get));
        }

        private Request<TradeSearch> buildRequest(long updateTimeStart, long updateTimeEnd) {
            Request<TradeSearch> request = new Request<>();
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
