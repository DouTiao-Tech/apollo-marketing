package com.doutiaotech.apollo.core.model.clickhouse;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Trade {

    private Long id;

    private Long name;

    private BigDecimal payment;

    //...

}
