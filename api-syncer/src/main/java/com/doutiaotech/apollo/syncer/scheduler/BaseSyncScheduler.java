package com.doutiaotech.apollo.syncer.scheduler;

import com.doutiaotech.apollo.core.concurrent.FutureTaskRegistry;
import com.doutiaotech.apollo.infrastructure.mysql.dao.SyncItemDao;
import com.doutiaotech.apollo.infrastructure.mysql.model.SyncItem;
import com.doutiaotech.apollo.infrastructure.mysql.model.SyncerType;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class BaseSyncScheduler {

    @Autowired
    protected SyncItemDao syncItemDao;

    private FutureTaskRegistry<Long> registry = new FutureTaskRegistry<>();

    @SneakyThrows
    @Scheduled(fixedDelay = 1000)
    public void schedule() {
        List<SyncItem> syncItems = syncItemDao.findByTypeAndStop(supportType(), false);
        Map<Long, SyncItem> idItemMap = syncItems.stream()
                .collect(Collectors.toMap(SyncItem::getId, Function.identity()));
        List<Long> needSyncItemIds = registry.purgeDoneAndRejectRunning(idItemMap.keySet());
        needSyncItemIds.forEach(syncItemId -> {
            Future<?> future = submitTask(idItemMap.get(syncItemId));
            registry.register(syncItemId, future);
        });
    }

    protected abstract Future<?> submitTask(SyncItem syncItem);

    protected abstract SyncerType supportType();

}
