package com.doutiaotech.apollo.infrastructure.mysql.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Data
public class User {

    @Id
    private Long id;

    private String shopName;

    private Boolean enabled;

    private LocalDateTime expireTime;

    @CreatedDate
    private LocalDateTime created;

    @LastModifiedDate
    private LocalDateTime lastModified;
}
