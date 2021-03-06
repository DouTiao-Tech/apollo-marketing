package com.doutiaotech.apollo.infrastructure.mysql.enums;

import com.doutiaotech.apollo.core.utils.JsonUtils;
import com.doutiaotech.apollo.infrastructure.mysql.model.SyncItem;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
public enum SyncType {

    /**
     * 抓取订单
     */
    FETCH_TRADE(LocalDateTime.class);

    /**
     * @see SyncItem#getProgress()
     */
    private Class<? extends Comparable<?>> progressType;

    @SuppressWarnings("unchecked")
    public <T extends Comparable<?>> T resolveProgress(@NonNull String json) {
        return (T) JsonUtils.fromJson(json, progressType);
    }

    public <T extends Comparable<?>> String toJson(@NonNull T progress) {
        return JsonUtils.toJson(progress);
    }

}
