package com.doutiaotech.apollo.infrastructure.mysql.dao;

import com.doutiaotech.apollo.infrastructure.mysql.model.SyncItem;
import com.doutiaotech.apollo.infrastructure.mysql.model.SyncType;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SyncItemDao extends CrudRepository<SyncItem, Long> {

    SyncItem findByShopIdAndType(Long shopId, SyncType type);

    List<SyncItem> findByTypeAndStop(SyncType type, boolean stop);

    @Query("select id from sync_item where type=:type and stop=:stop")
    List<Long> findIdByTypeAndStop(@Param("type") SyncType type, @Param("stop") boolean stop);

}
