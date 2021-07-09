package com.doutiaotech.apollo.core.model.clickhouse;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Trade {

    private Long id;

    private Long shopId;

    private String name;

    private BigDecimal payment;

    private LocalDateTime created;

    private LocalDateTime modified;

}
