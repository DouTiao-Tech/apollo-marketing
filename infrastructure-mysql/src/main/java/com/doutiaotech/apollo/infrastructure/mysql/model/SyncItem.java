package com.doutiaotech.apollo.infrastructure.mysql.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

@Data
public class SyncItem {

    @Id
    private Long id;

    @Version
    private Long version;

    /**
     * @see User#getId()
     */
    private Long shopId;

    private SyncerType type;

    /**
     * 是否停止同步
     */
    private boolean stop;

    /**
     * 当前同步进度
     */
    private String progress;

    /**
     * 首次同步起始时间
     */
    private String start;

    /**
     * 同步终止时间
     */
    private String end;

}
