package com.doutiaotech.apollo.syncer.scheduler;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import com.doutiaotech.apollo.infrastructure.mysql.enums.SyncType;
import com.doutiaotech.apollo.infrastructure.mysql.model.SyncItem;
import com.google.common.util.concurrent.MoreExecutors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SyncSchedulerTest.TestSyncScheduler.class)
public class SyncSchedulerTest {

    private static final SyncType testSyncType = SyncType.FETCH_TRADE;

    private static final LocalDateTime start = LocalDateTime.now();
    private static final LocalDateTime end = LocalDateTime.now();

    @Autowired
    TestSyncScheduler testSyncScheduler;

    @Test
    public void test_syncScheduler() {
        testSyncScheduler.syncItem = mockSyncItem(1L);
        testSyncScheduler.value = 1L;
        testSyncScheduler.result = 2L;
        testSyncScheduler.schedule();
        assertThat(testSyncScheduler.value, is(2L));
        assertThat(testSyncScheduler.registry.isRunning(1L), is(false));
    }

    private SyncItem mockSyncItem(long id) {
        SyncItem syncItem = new SyncItem();
        syncItem.setId(id);
        syncItem.setShopId(1L);
        syncItem.setType(testSyncType);
        syncItem.setStart(testSyncType.toJson(start));
        syncItem.setProgress(testSyncType.toJson(start));
        syncItem.setEnd(testSyncType.toJson(end));
        return syncItem;
    }

    @Component
    static class TestSyncScheduler extends BaseSyncScheduler {

        SyncItem syncItem;

        long value;

        long result;

        @Override
        protected Future<?> submitTask(SyncItem syncItem) {
            FutureTask<Long> task = new FutureTask<>(() -> value = result);
            MoreExecutors.directExecutor().execute(task);
            return task;
        }

        @Override
        protected List<SyncItem> findSyncItem() {
            return Collections.singletonList(syncItem);
        }

    }

}
