package com.doutiaotech.apollo.syncer.scheduler;

import com.doutiaotech.apollo.core.utils.DateTimeUtils;
import com.doutiaotech.apollo.external.dy.api.OrderApi;
import com.doutiaotech.apollo.external.dy.request.Request;
import com.doutiaotech.apollo.external.dy.request.TradeSearch;
import com.doutiaotech.apollo.external.dy.response.Response;
import com.doutiaotech.apollo.external.dy.response.TradeSearchPage;
import com.doutiaotech.apollo.infrastructure.mysql.model.SyncItem;
import com.doutiaotech.apollo.infrastructure.mysql.model.SyncerType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@Slf4j
@Component
public class TradeSyncScheduler extends BaseSyncScheduler {

    @Autowired
    private OrderApi orderApi;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ExecutorService tradeSyncExecutor;

    @Override
    protected SyncerType supportType() {
        return SyncerType.FETCH_TRADE;
    }

    @Override
    protected Future<?> submitTask(Long syncItemId) {
        return tradeSyncExecutor.submit(new TradeSyncTask(syncItemId));
    }

    class TradeSyncTask implements Runnable {

        private SyncItem syncItem;

        TradeSyncTask(Long syncItemId) {
            syncItem = syncItemDao.findById(syncItemId).orElseThrow(AssertionError::new);
        }

        @Override
        public void run() {
            LocalDateTime progress = syncItem.resolveProgress();
            LocalDateTime end = syncItem.resolveEnd();
            long updateTimeStart = DateTimeUtils.toTimestamp(progress);
            long updateTimeEnd = DateTimeUtils.toTimestamp(end);
            while (!syncItem.isFinish()) {
                Request<TradeSearch> request = buildRequest(updateTimeStart, updateTimeEnd);
                Response<TradeSearchPage> response = orderApi.searchList(request);
                progressResponse(response);
                if (response.getData().getSize() == 0) {
                    updateTimeStart = updateTimeEnd;
                } else {
                    updateTimeStart = nextUpdateTimeStart(response);
                }
                syncItem.updateProgress(DateTimeUtils.longToDateTime(updateTimeEnd));
                syncItemDao.save(syncItem);
            }
        }

        private long nextUpdateTimeStart(Response<TradeSearchPage> response) {
            return response.getData().getShop_order_list().stream()
                    .mapToLong(TradeSearchPage.ShopOrderListBean::getUpdate_time)
                    .max().orElseThrow(AssertionError::new);
        }

        private void progressResponse(Response<TradeSearchPage> response) {
            // TODO: response to json
            kafkaTemplate.send("trade", "json data");
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
