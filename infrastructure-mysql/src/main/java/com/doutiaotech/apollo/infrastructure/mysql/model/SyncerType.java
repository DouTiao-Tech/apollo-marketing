package com.doutiaotech.apollo.infrastructure.mysql.model;

import com.doutiaotech.apollo.core.utils.JsonUtils;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
public enum SyncerType {

    /**
     * 抓取订单
     */
    FETCH_TRADE(LocalDateTime.class);


    /**
     * @see SyncItem#getProgress()
     */
    private Class<?> progressType;

    @SuppressWarnings("unchecked")
    public <T> T resolveProgress(String json) {
        return (T) JsonUtils.fromJson(json, progressType);
    }

}
