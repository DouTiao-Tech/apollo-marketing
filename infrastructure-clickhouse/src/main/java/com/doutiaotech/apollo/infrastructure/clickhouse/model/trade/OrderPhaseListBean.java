package com.doutiaotech.apollo.infrastructure.clickhouse.model.trade;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderPhaseListBean {
    private String phase_order_id;
    private int total_phase;
    private int current_phase;
    private boolean pay_success;
    private String sku_order_id;
    private long campaign_id;
    private int phase_payable_price;
    private int phase_pay_type;
    private int phase_open_time;
    private int phase_pay_time;
    private int phase_close_time;
    private String channel_payment_no;
    private int phase_order_amount;
    private int phase_sum_amount;
    private int phase_post_amount;
    private int phase_pay_amount;
    private int phase_promotion_amount;
    private String current_phase_status_desc;
    private int sku_price;
}
