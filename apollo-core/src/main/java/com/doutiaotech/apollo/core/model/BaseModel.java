package com.doutiaotech.apollo.core.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Data;

@Data
public class BaseModel {

    @CreatedDate
    private LocalDateTime created;

    @LastModifiedDate
    private LocalDateTime lastModified;
}
