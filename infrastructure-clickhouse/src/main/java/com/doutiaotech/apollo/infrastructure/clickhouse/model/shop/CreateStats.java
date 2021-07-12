package com.doutiaotech.apollo.infrastructure.clickhouse.model.shop;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateStats {

    /**
     * 当日创建的订单数
     */
    private int createNum;

    /**
     * 当日创建的订单金额
     */
    private BigDecimal createAmount = BigDecimal.ZERO;

    /**
     * 付款订单数(创建订单已付款)
     */
    private int paidNum;

    /**
     * 付款订单金额
     */
    private BigDecimal paidAmount = BigDecimal.ZERO;

    /**
     * 付款以前关闭的订单（流失的订单）
     */
    private int closedBeforePayNum;

    /**
     * 付款以前关闭的订单金额
     */
    private BigDecimal closedBeforePayAmount = BigDecimal.ZERO;

}
