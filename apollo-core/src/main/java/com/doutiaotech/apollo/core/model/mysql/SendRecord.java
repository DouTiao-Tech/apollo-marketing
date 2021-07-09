package com.doutiaotech.apollo.core.model.mysql;

import com.doutiaotech.apollo.core.model.BaseModel;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import lombok.Data;

@Data
public class SendRecord extends BaseModel {

    @Id
    private Long id;

    @Version
    private Long version;

    /**
     * @see SmsTemplate#getId()
     */
    private Long templateId;

    private String params;

}
