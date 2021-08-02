package com.doutiaotech.apollo.infrastructure.mysql.dao;

import com.doutiaotech.apollo.infrastructure.mysql.enums.SyncType;
import com.doutiaotech.apollo.infrastructure.mysql.model.SyncItem;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface SyncItemDao extends CrudRepository<SyncItem, Long> {

    SyncItem findByShopIdAndType(Long shopId, SyncType type);

    List<SyncItem> findByTypeAndStopAndProgressLessThan(SyncType type, boolean stop, String maxProgress);

    @Query("select id from sync_item where type=:type and stop=:stop")
    List<Long> findIdByTypeAndStop(@Param("type") SyncType type, @Param("stop") boolean stop);

}
