package com.doutiaotech.apollo.infrastructure.clickhouse.model.trade;

import lombok.Data;

import java.util.List;

@Data
public class Trade {
    private int shop_id;
    private String shop_name;
    private String open_id;
    private String order_id;
    private int order_level;
    private int biz;
    private String biz_desc;
    private int order_type;
    private String order_type_desc;
    private int trade_type;
    private String trade_type_desc;
    private int order_status;
    private String order_status_desc;
    private int main_status;
    private String main_status_desc;
    private int pay_time;
    private int order_expire_time;
    private int finish_time;
    private int create_time;
    private int update_time;
    private String cancel_reason;
    private String buyer_words;
    private String seller_words;
    private int b_type;
    private String b_type_desc;
    private int sub_b_type;
    private String sub_b_type_desc;
    private long app_id;
    private int pay_type;
    private String channel_payment_no;
    private int order_amount;
    private int pay_amount;
    private int post_amount;
    private int post_insurance_amount;
    private int modify_amount;
    private int modify_post_amount;
    private int promotion_amount;
    private int promotion_shop_amount;
    private int promotion_platform_amount;
    private int shop_cost_amount;
    private int platform_cost_amount;
    private int promotion_talent_amount;
    private int promotion_pay_amount;
    private String post_tel;
    private String post_receiver;
    private PostAddrBean post_addr;
    private int exp_ship_time;
    private int ship_time;
    private int seller_remark_stars;
    private String doudian_open_id;
    private List<LogisticsInfoBean> logistics_info;
    private List<SkuOrderListBean> sku_order_list;
    private List<OrderPhaseListBean> order_phase_list;

}