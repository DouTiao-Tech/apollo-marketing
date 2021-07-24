create stream if not exists trade_stream (
    shop_id                   int,
    shop_name                 string,
    open_id                   bigint,
    order_id                  bigint,
    order_level               int,
    biz                       int,
    biz_desc                  string,
    order_type                int,
    order_type_desc           string,
    trade_type                int,
    trade_type_desc           string,
    order_status              int,
    order_status_desc         string,
    main_status               int,
    main_status_desc          string,
    pay_time                  timestamp,
    order_expire_time         timestamp,
    finish_time               timestamp,
    create_time               timestamp,
    update_time               timestamp,
    cancel_reason             string,
    buyer_words               string,
    seller_words              string,
    b_type                    int,
    b_type_desc               string,
    sub_b_type                int,
    sub_b_type_desc           string,
    app_id                    bigint,
    pay_type                  int,
    channel_payment_no        string,
    order_amount              int,
    pay_amount                int,
    post_amount               int,
    post_insurance_amount     int,
    modify_amount             int,
    modify_post_amount        int,
    promotion_amount          int,
    promotion_shop_amount     int,
    promotion_platform_amount int,
    shop_cost_amount          int,
    platform_cost_amount      int,
    promotion_talent_amount   int,
    promotion_pay_amount      int,
    post_tel                  string,
    post_receiver             string,
    post_addr                 string, -- json
    exp_ship_time             timestamp,
    ship_time                 timestamp,
    seller_remark_stars       int,
    doudian_open_id           string
) with (kafka_topic='trade',value_format='json');

create table if not exists shop_daily_stats
with (kafka_topic='shop-daily-stats',value_format='json',partitions=2, replicas=1)
as
select
shop_id, stats_day,
count_distinct(order_id) as create_order,
-- TODO：other stats
group by shop_id, datetostring(create_time, 'yyyy-mm-dd')
emit changes;