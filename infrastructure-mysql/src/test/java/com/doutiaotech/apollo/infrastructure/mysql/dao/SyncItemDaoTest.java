package com.doutiaotech.apollo.infrastructure.mysql.dao;

import com.doutiaotech.apollo.infrastructure.mysql.BaseDaoTest;
import com.doutiaotech.apollo.infrastructure.mysql.model.SyncItem;
import com.doutiaotech.apollo.infrastructure.mysql.model.SyncType;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SyncItemDaoTest extends BaseDaoTest {

    @Autowired
    SyncItemDao syncItemDao;

    @Test
    public void test_findIdByTypeAndStop() {
        syncItemDao.save(newSyncItem(1L, SyncType.FETCH_TRADE, 1, 5, 10, 2, false));
        syncItemDao.save(newSyncItem(2L, SyncType.FETCH_TRADE, 2, 4, 20, 1, false));
        syncItemDao.save(newSyncItem(3L, SyncType.FETCH_TRADE, 3, 8, 30, 1, true));
        Assert.assertEquals(2, syncItemDao.findIdByTypeAndStop(SyncType.FETCH_TRADE, false).size());
        syncItemDao.deleteAll();
    }

    @Test
    public void test_findByTypeAndStop() {
        syncItemDao.save(newSyncItem(1L, SyncType.FETCH_TRADE, 1, 5, 10, 2, false));
        syncItemDao.save(newSyncItem(2L, SyncType.FETCH_TRADE, 2, 4, 20, 1, false));
        syncItemDao.save(newSyncItem(3L, SyncType.FETCH_TRADE, 3, 8, 30, 1, true));
        Assert.assertEquals(2, syncItemDao.findByTypeAndStop(SyncType.FETCH_TRADE, false).size());
        syncItemDao.deleteAll();
    }

    private SyncItem newSyncItem(long shopId, SyncType syncType, long start, long progress, long end, long step, boolean stop) {
        SyncItem syncItem = new SyncItem();
        syncItem.setShopId(shopId);
        syncItem.setType(syncType);
        syncItem.setStart(Long.toString(start));
        syncItem.setProgress(Long.toString(progress));
        syncItem.setEnd(Long.toString(end));
        syncItem.setStep(step);
        syncItem.setStop(stop);
        return syncItem;
    }

}
