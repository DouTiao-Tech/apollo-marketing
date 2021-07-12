package com.doutiaotech.apollo.infrastructure.clickhouse.model;

import com.doutiaotech.apollo.infrastructure.clickhouse.model.shop.ConfirmStats;
import com.doutiaotech.apollo.infrastructure.clickhouse.model.shop.ConsignStats;
import com.doutiaotech.apollo.infrastructure.clickhouse.model.shop.CreateStats;
import com.doutiaotech.apollo.infrastructure.clickhouse.model.shop.PayStats;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ShopDailyStats {

    /**
     * 卖家ID
     */
    private long shopId;

    /**
     * 统计日期
     */
    private LocalDate statsDay;

    /**
     * 基于createTime统计的字段，当天创建的订单
     */
    private CreateStats createStats = new CreateStats();

    /**
     * 基于payTime统计的字段，当天付款的订单
     */
    private PayStats payStats = new PayStats();

    /**
     * 基于签收时间统计的字段
     */
    private ConsignStats consignStats = new ConsignStats();

    /**
     * 基于confirmTime统计的字段，当天确认的订单
     */
    private ConfirmStats confirmStats = new ConfirmStats();

}
