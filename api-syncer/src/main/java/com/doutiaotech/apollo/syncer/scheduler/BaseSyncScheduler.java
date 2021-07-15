package com.doutiaotech.apollo.syncer.scheduler;

import com.doutiaotech.apollo.core.concurrent.FutureTaskRegistry;
import com.doutiaotech.apollo.infrastructure.mysql.dao.SyncItemDao;
import com.doutiaotech.apollo.infrastructure.mysql.model.SyncerType;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;
import java.util.concurrent.Future;

public abstract class BaseSyncScheduler {

    @Autowired
    protected SyncItemDao syncItemDao;

    private FutureTaskRegistry<Long> registry = new FutureTaskRegistry<>();

    @SneakyThrows
    @Scheduled(fixedDelay = 1000)
    public void schedule() {
        List<Long> syncItemIds = syncItemDao.findIdByTypeAndStop(supportType(), false);
        List<Long> needSyncItemIds = registry.purgeDoneAndRejectRunning(syncItemIds);
        needSyncItemIds.forEach(syncItemId -> {
            Future<?> future = submitTask(syncItemId);
            registry.register(syncItemId, future);
        });
    }

    protected abstract Future<?> submitTask(Long syncItemId);

    protected abstract SyncerType supportType();

}
