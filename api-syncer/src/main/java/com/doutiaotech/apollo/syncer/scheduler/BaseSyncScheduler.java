package com.doutiaotech.apollo.syncer.scheduler;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.doutiaotech.apollo.core.concurrent.FutureTaskRegistry;
import com.doutiaotech.apollo.infrastructure.mysql.model.SyncItem;

import org.springframework.scheduling.annotation.Scheduled;

import lombok.SneakyThrows;

public abstract class BaseSyncScheduler {

    FutureTaskRegistry<Long> registry = new FutureTaskRegistry<>();

    @SneakyThrows
    @Scheduled(fixedDelay = 1000)
    public void schedule() {
        List<SyncItem> syncItems = findSyncItem();
        Map<Long, SyncItem> idItemMap = syncItems.stream()
                .collect(Collectors.toMap(SyncItem::getId, Function.identity()));
        List<Long> needSyncItemIds = registry.purgeDoneAndRejectRunning(idItemMap.keySet());
        needSyncItemIds.forEach(syncItemId -> {
            Future<?> future = submitTask(idItemMap.get(syncItemId));
            registry.register(syncItemId, future);
        });
    }

    protected abstract List<SyncItem> findSyncItem();

    protected abstract Future<?> submitTask(SyncItem syncItem);

}
