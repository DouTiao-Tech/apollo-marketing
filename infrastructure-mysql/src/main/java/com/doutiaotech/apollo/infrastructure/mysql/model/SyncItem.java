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

    private boolean stop;

    private String progress;

    private String start;

    private String end;

}
