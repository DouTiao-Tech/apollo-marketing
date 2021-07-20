-- refer to: https://clickhouse.tech/docs/en/

create database if not exists apollo;

-- https://op.jinritemai.com/docs/api-docs/15/555
CREATE TABLE apollo.trade_kafka_consumer
(
    shop_id                   Int32,
    shop_name                 String,
    open_id                   Int64,
    order_id                  Int64,
    order_level               Int32,
    biz                       Int32,
    biz_desc                  String,
    order_type                Int8,
    order_type_desc           String,
    trade_type                Int8,
    trade_type_desc           String,
    order_status              Int32,
    order_status_desc         String,
    main_status               Int32,
    main_status_desc          String,
    pay_time                  DateTime,
    order_expire_time         DateTime,
    finish_time               DateTime,
    create_time               DateTime,
    update_time               DateTime,
    cancel_reason             String,
    buyer_words               String,
    seller_words              String,
    b_type                    Int8,
    b_type_desc               String,
    sub_b_type                Int8,
    sub_b_type_desc           String,
    app_id                    Int64,
    pay_type                  Int8,
    channel_payment_no        String,
    order_amount              Int32,
    pay_amount                Int32,
    post_amount               Int32,
    post_insurance_amount     Int32,
    modify_amount             Int32,
    modify_post_amount        Int32,
    promotion_amount          Int32,
    promotion_shop_amount     Int32,
    promotion_platform_amount Int32,
    shop_cost_amount          Int32,
    platform_cost_amount      Int32,
    promotion_talent_amount   Int32,
    promotion_pay_amount      Int32,
    post_tel                  String,
    post_receiver             String,
    post_addr                 String, -- json
    exp_ship_time             DateTime,
    ship_time                 DateTime,
    seller_remark_stars       Int32,
    doudian_open_id           String,
    logistics_info Nested(
        tracking_no String,
        company String,
        ship_time DateTime,
        delivery_id String,
        company_name String,
        product_info String
        ),
    sku_order_list Nested(
        order_id Int64,
        parent_order_id Int64,
        order_level Int32,
        biz Int32,
        biz_desc String,
        order_type Int8,
        order_type_desc String,
        trade_type Int8,
        trade_type_desc String,
        order_status Int32,
        order_status_desc String,
        main_status Int32,
        main_status_desc String,
        pay_time DateTime,
        order_expire_time DateTime,
        finish_time DateTime,
        create_time DateTime,
        update_time DateTime,
        cancel_reason String,
        b_type Int8,
        b_type_desc String,
        sub_b_type Int8,
        sub_b_type_desc String,
        send_pay Int32,
        send_pay_desc String,
        author_id Int32,
        author_name String,
        theme_type String,
        theme_type_desc String,
        app_id Int64,
        room_id Int64,
        content_id String,
        video_id String,
        origin_id String,
        cid Int64,
        c_biz Int32,
        c_biz_desc String,
        page_id Int64,
        pay_type Int8,
        channel_payment_no String,
        order_amount Int32,
        pay_amount Int32,
        post_insurance_amount Int32,
        modify_amount Int32,
        modify_post_amount Int32,
        promotion_amount Int32,
        promotion_shop_amount Int32,
        promotion_platform_amount Int32,
        shop_cost_amount Int32,
        platform_cost_amount Int32,
        promotion_talent_amount Int32,
        promotion_pay_amount Int32,
        code String,
        post_tel String,
        post_receiver String,
        post_addr String ,            -- json
        exp_ship_time DateTime,
        ship_time DateTime,
        logistics_receipt_time DateTime,
        confirm_receipt_time DateTime,
        goods_type Int8,
        product_id Int64,
        sku_id Int32,
        first_cid Int32,
        second_cid Int32,
        third_cid Int32,
        fourth_cid Int32,
        out_sku_id String,
        supplier_id String,
        out_product_id String,
        inventory_type String,
        inventory_type_desc String,
        reduce_stock_type Int8,
        reduce_stock_type_desc String,
        origin_amount Int32,
        has_tax UInt8,
        item_num Int32,
        sum_amount Int32,
        source_platform String,
        product_pic String,
        is_comment Int32,
        product_name String,
        post_amount Int32,
        pre_sale_type Int8,
        after_sale_status Int8,
        after_sale_type Int8,
        refund_status Int8
        --         ,spec List,
--         warehouse_ids List,
--         out_warehouse_ids List,
--         inventory_list List
        ),
    order_phase_list Nested(
        phase_order_id Int64,
        total_phase Int32,
        current_phase Int32,
        pay_success UInt8,
        sku_order_id Int64,
        campaign_id Int64,
        phase_payable_price Int32,
        phase_pay_type Int8,
        phase_open_time DateTime,
        phase_pay_time DateTime,
        phase_close_time DateTime,
        channel_payment_no String,
        phase_order_amount Int32,
        phase_sum_amount Int32,
        phase_post_amount Int32,
        phase_pay_amount Int32,
        phase_promotion_amount Int32,
        current_phase_status_desc String,
        sku_price Int32
        )
) engine = Kafka settings kafka_broker_list = 'kafka-broker:9092',
    kafka_topic_list = 'trade',
    kafka_group_name = 'clickhouse.trade',
    kafka_format = 'JSONEachRow',
    kafka_num_consumers = 1;

create table if not exists apollo.trade
(
    shop_id                         Int32,
    shop_name                       String,
    open_id                         Int64,
    order_id                        Int64,
    order_level                     Int32,
    biz                             Int32,
    biz_desc                        String,
    order_type                      Int8,
    order_type_desc                 String,
    trade_type                      Int8,
    trade_type_desc                 String,
    order_status                    Int32,
    order_status_desc               String,
    main_status                     Int32,
    main_status_desc                String,
    pay_time                        DateTime,
    order_expire_time               DateTime,
    finish_time                     DateTime,
    create_time                     DateTime,
    update_time                     DateTime,
    cancel_reason                   String,
    buyer_words                     String,
    seller_words                    String,
    b_type                          Int8,
    b_type_desc                     String,
    sub_b_type                      Int8,
    sub_b_type_desc                 String,
    app_id                          Int64,
    pay_type                        Int8,
    channel_payment_no              String,
    order_amount                    Int32,
    pay_amount                      Int32,
    post_amount                     Int32,
    post_insurance_amount           Int32,
    modify_amount                   Int32,
    modify_post_amount              Int32,
    promotion_amount                Int32,
    promotion_shop_amount           Int32,
    promotion_platform_amount       Int32,
    shop_cost_amount                Int32,
    platform_cost_amount            Int32,
    promotion_talent_amount         Int32,
    promotion_pay_amount            Int32,
    post_tel                        String,
    post_receiver                   String,
    post_addr_province_name         String,
    post_addr_province_id           String,
    post_addr_city_name             String,
    post_addr_city_id               String,
    post_addr_town_name             String,
    post_addr_town_id               String,
    post_addr_street_encrypt_detail String,
    exp_ship_time                   DateTime,
    ship_time                       DateTime,
    seller_remark_stars             Int32,
    doudian_open_id                 String,
    logistics_info Nested(
        tracking_no String,
        company String,
        ship_time DateTime,
        delivery_id String,
        company_name String,
        product_info String
        ),
    sku_order_list Nested(
        order_id Int64,
        parent_order_id Int64,
        order_level Int32,
        biz Int32,
        biz_desc String,
        order_type Int8,
        order_type_desc String,
        trade_type Int8,
        trade_type_desc String,
        order_status Int32,
        order_status_desc String,
        main_status Int32,
        main_status_desc String,
        pay_time DateTime,
        order_expire_time DateTime,
        finish_time DateTime,
        create_time DateTime,
        update_time DateTime,
        cancel_reason String,
        b_type Int8,
        b_type_desc String,
        sub_b_type Int8,
        sub_b_type_desc String,
        send_pay Int32,
        send_pay_desc String,
        author_id Int32,
        author_name String,
        theme_type String,
        theme_type_desc String,
        app_id Int64,
        room_id Int64,
        content_id String,
        video_id String,
        origin_id String,
        cid Int64,
        c_biz Int32,
        c_biz_desc String,
        page_id Int64,
        pay_type Int8,
        channel_payment_no String,
        order_amount Int32,
        pay_amount Int32,
        post_insurance_amount Int32,
        modify_amount Int32,
        modify_post_amount Int32,
        promotion_amount Int32,
        promotion_shop_amount Int32,
        promotion_platform_amount Int32,
        shop_cost_amount Int32,
        platform_cost_amount Int32,
        promotion_talent_amount Int32,
        promotion_pay_amount Int32,
        code String,
        post_tel String,
        post_receiver String,
        post_addr_province_name String,
        post_addr_province_id String,
        post_addr_city_name String,
        post_addr_city_id String,
        post_addr_town_name String,
        post_addr_town_id String,
        post_addr_street_encrypt_detail String,
        exp_ship_time DateTime,
        ship_time DateTime,
        logistics_receipt_time DateTime,
        confirm_receipt_time DateTime,
        goods_type Int8,
        product_id Int64,
        sku_id Int32,
        first_cid Int32,
        second_cid Int32,
        third_cid Int32,
        fourth_cid Int32,
        out_sku_id String,
        supplier_id String,
        out_product_id String,
        inventory_type String,
        inventory_type_desc String,
        reduce_stock_type Int8,
        reduce_stock_type_desc String,
        origin_amount Int32,
        has_tax UInt8,
        item_num Int32,
        sum_amount Int32,
        source_platform String,
        product_pic String,
        is_comment Int32,
        product_name String,
        post_amount Int32,
        pre_sale_type Int8,
        after_sale_status Int8,
        after_sale_type Int8,
        refund_status Int8
        --         ,spec List,
--         warehouse_ids List,
--         out_warehouse_ids List,
--         inventory_list List
        ),
    order_phase_list Nested(
        phase_order_id Int64,
        total_phase Int32,
        current_phase Int32,
        pay_success UInt8,
        sku_order_id Int64,
        campaign_id Int64,
        phase_payable_price Int32,
        phase_pay_type Int8,
        phase_open_time DateTime,
        phase_pay_time DateTime,
        phase_close_time DateTime,
        channel_payment_no String,
        phase_order_amount Int32,
        phase_sum_amount Int32,
        phase_post_amount Int32,
        phase_pay_amount Int32,
        phase_promotion_amount Int32,
        current_phase_status_desc String,
        sku_price Int32
        )
) engine = ReplacingMergeTree(update_time) partition by toYYYYMMDD(create_time)
      order by (shop_id, order_id);

-- create materialized view apollo.trade_mv to apollo.trade as
-- select *
-- from apollo.trade_kafka_consumer;


create materialized view apollo.shop_daily_stats_mv engine = AggregatingMergeTree() partition by shop_id
    order by (shop_id, stats_day) as
select shop_id,
       toDate(create_time)                         as stats_day,
       uniqState(order_id)                         as create_order_num,
       uniqState(doudian_open_id)                  as create_customer_num,
       sumState(order_amount)                      as create_order_amount,
       uniqIfState(order_id, pay_time is not null) as pay_order_num,
       sumState(pay_amount)                        as pay_order_amount,
       uniqIfState(order_id, main_status = 103)    as close_order_num,
       sumIfState(order_amount, main_status = 103) as close_order_amount
from apollo.trade
group by shop_id,
         toDate(create_time);

create materialized view apollo.shop_customer_stats_mv engine = AggregatingMergeTree() partition by shop_id
    order by (shop_id, doudian_open_id) as
select shop_id,
       doudian_open_id,
       uniqState(order_id)                         as create_order_num,
       uniqIfState(order_id, pay_time is not null) as pay_order_num,
       sumState(pay_amount)                        as pay_order_amount,
       maxOrNullState(create_time)                 as last_create_time,
       maxState(pay_time)                          as last_pay_time
from apollo.trade
group by shop_id,
         doudian_open_id;