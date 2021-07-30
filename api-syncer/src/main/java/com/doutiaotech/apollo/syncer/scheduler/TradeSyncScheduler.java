package com.doutiaotech.apollo.syncer.scheduler;

import com.doutiaotech.apollo.core.utils.DateTimeUtils;
import com.doutiaotech.apollo.external.dy.api.OrderApi;
import com.doutiaotech.apollo.external.dy.request.Request;
import com.doutiaotech.apollo.external.dy.request.TradeSearch;
import com.doutiaotech.apollo.external.dy.response.Response;
import com.doutiaotech.apollo.external.dy.response.TradeSearchPage;
import com.doutiaotech.apollo.external.dy.response.TradeSearchPage.ShopOrderListBean;
import com.doutiaotech.apollo.infrastructure.mysql.dao.SyncItemDao;
import com.doutiaotech.apollo.infrastructure.mysql.enums.SyncType;
import com.doutiaotech.apollo.infrastructure.mysql.model.SyncItem;
import com.doutiaotech.apollo.syncer.tx.TradeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Future;

@Slf4j
@Component
public class TradeSyncScheduler extends BaseSyncScheduler {

    @Autowired
    private SyncItemDao syncItemDao;

    @Autowired
    private OrderApi orderApi;

    @Autowired
    private ThreadPoolTaskExecutor tradeSyncExecutor;

    @Autowired
    private TradeService tradeService;

    @Override
    protected List<SyncItem> findSyncItem() {
        // TODO: time filter
        return syncItemDao.findByTypeAndStop(SyncType.FETCH_TRADE, false);
    }

    @Override
    protected Future<?> submitTask(SyncItem syncItem) {
        return tradeSyncExecutor.submit(new TradeSyncTask(syncItem));
    }

    class TradeSyncTask implements Runnable {

        SyncItem syncItem;

        TradeSyncTask(SyncItem syncItem) {
            this.syncItem = syncItem;
        }

        @Override
        public void run() {
            LocalDateTime progress = syncItem.resolveProgress();
            LocalDateTime end = syncItem.resolveEnd();
            long updateTimeStart = DateTimeUtils.toTimestamp(progress);
            long updateTimeEnd = DateTimeUtils.toTimestamp(end);
            log.info("start sync trade for user#{} between {} and {}", syncItem.getShopId(), progress, end);
            try {
                long sizeSum = 0;
                while (!syncItem.isFinish()) {
                    Request<TradeSearch> request = buildRequest(updateTimeStart, updateTimeEnd);
                    Response<TradeSearchPage> response = orderApi.searchList(request);
                    TradeSearchPage data = response.getData();
                    if (CollectionUtils.isEmpty(data.getShop_order_list())) {
                        updateTimeStart = updateTimeEnd;
                    } else {
                        sizeSum += tradeService.sendToKafka(data.getShop_order_list());
                        updateTimeStart = nextUpdateTimeStart(data.getShop_order_list());
                    }
                    syncItem.updateProgress(DateTimeUtils.longToDateTime(updateTimeStart));
                    syncItem = syncItemDao.save(syncItem);
                }
                log.info("sync {} trade to kafka for user#{} updateTime between {} ~ {}", sizeSum, syncItem.getShopId(),
                        DateTimeUtils.longToDateTime(updateTimeStart), DateTimeUtils.longToDateTime(updateTimeEnd));
            } catch (Exception e) {
                log.error("sync trade error, syncItem:" + syncItem, e);
            }
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
