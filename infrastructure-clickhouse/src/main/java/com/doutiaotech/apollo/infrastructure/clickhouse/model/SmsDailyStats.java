package com.doutiaotech.apollo.infrastructure.clickhouse.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SmsDailyStats {

    private Long shopId;

    private LocalDate statsDay;

    private int commitNum;

    private int commitSuccessNum;

    private int successNum;

    private int failureNum;

}
