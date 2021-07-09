package com.doutiaotech.apollo.core.model.mysql;

import com.doutiaotech.apollo.core.model.BaseModel;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class User extends BaseModel {

    @Id
    private Long id;

    private String shopName;

}
