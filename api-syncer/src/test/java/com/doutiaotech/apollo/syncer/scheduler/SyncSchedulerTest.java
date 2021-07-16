package com.doutiaotech.apollo.syncer.scheduler;

import com.doutiaotech.apollo.infrastructure.mysql.dao.SyncItemDao;
import com.doutiaotech.apollo.infrastructure.mysql.model.SyncItem;
import com.doutiaotech.apollo.infrastructure.mysql.model.SyncType;
import com.google.common.util.concurrent.MoreExecutors;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = SyncSchedulerTest.TestSyncScheduler.class,
        properties = "spring.main.banner-mode=off"
)
public class SyncSchedulerTest {

    private static final SyncType testSyncType = SyncType.FETCH_TRADE;

    private static final LocalDateTime start = LocalDateTime.now();
    private static final LocalDateTime end = LocalDateTime.now();

    @MockBean
    SyncItemDao syncItemDao;

    @Autowired
    TestSyncScheduler testSyncScheduler;

    @Test
    public void test_syncScheduler() {
        Mockito.when(syncItemDao.findByTypeAndStop(testSyncType, false))
                .thenReturn(Collections.singletonList(buildSyncItem()));
        testSyncScheduler.value = 1L;
        testSyncScheduler.result = 2L;
        testSyncScheduler.schedule();
        Assert.assertEquals(2L, testSyncScheduler.value);
    }

    private SyncItem buildSyncItem() {
        SyncItem syncItem = new SyncItem();
        syncItem.setId(1L);
        syncItem.setShopId(1L);
        syncItem.setType(testSyncType);
        syncItem.setStart(testSyncType.toJson(start));
        syncItem.setProgress(testSyncType.toJson(start));
        syncItem.setEnd(testSyncType.toJson(end));
        return syncItem;
    }


    @Component
    static class TestSyncScheduler extends BaseSyncScheduler {

        long value;

        long result;

        @Override
        protected Future<?> submitTask(SyncItem syncItem) {
            FutureTask<Long> task = new FutureTask<>(() -> value = result);
            MoreExecutors.directExecutor().execute(task);
            return task;
        }

        @Override
        protected SyncType supportType() {
            return testSyncType;
        }
    }

}