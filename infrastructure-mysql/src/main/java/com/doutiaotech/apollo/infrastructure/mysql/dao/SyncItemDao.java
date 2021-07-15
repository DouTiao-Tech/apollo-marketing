package com.doutiaotech.apollo.infrastructure.mysql.dao;

import com.doutiaotech.apollo.infrastructure.mysql.model.SyncItem;
import com.doutiaotech.apollo.infrastructure.mysql.model.SyncerType;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SyncItemDao extends CrudRepository<SyncItem, Long> {

    SyncItem findByTypeAndShopId(SyncerType type, Long shopId);

    @Query("select id from type=:type and stop=:stop")
    List<Long> findIdByTypeAndStop(SyncerType type, boolean stop);

}
