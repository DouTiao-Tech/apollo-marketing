package com.doutiaotech.apollo.infrastructure.mysql.model;

import com.doutiaotech.apollo.infrastructure.mysql.enums.SyncType;
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

    private SyncType type;

    /**
     * 是否停止同步
     */
    private Boolean stop;

    /**
     * 当前同步进度
     *
     * @see SyncType
     */
    private String progress;

    /**
     * 首次同步起始
     *
     * @see SyncType
     */
    private String start;

    /**
     * 同步终止
     *
     * @see SyncType
     */
    private String end;

    /**
     * 同步步长
     */
    private Long step;

    public boolean isFinish() {
        Comparable<Object> progress = resolveProgress();
        Comparable<Object> end = resolveEnd();
        return progress.compareTo(end) >= 0;
    }

    public <T extends Comparable<?>> T resolveProgress() {
        return getType().resolveProgress(getProgress());
    }

    public <T extends Comparable<?>> T resolveEnd() {
        return getType().resolveProgress(getEnd());
    }

    public <T extends Comparable<?>> void updateProgress(T progress) {
        this.progress = getType().toJson(progress);
    }

}
