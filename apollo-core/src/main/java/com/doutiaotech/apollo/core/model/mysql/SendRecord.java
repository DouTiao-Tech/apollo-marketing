package com.doutiaotech.apollo.core.model.mysql;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import lombok.Data;

@Data
public class SendRecord {

    @Id
    private Long id;

    @Version
    private Long version;

    /**
     * @see SmsTemplate#getId()
     */
    private Long templateId;

    private String params;

    @CreatedDate
    private LocalDateTime created;

    @LastModifiedDate
    private LocalDateTime lastModified;
}
