-- refer to: https://clickhouse.tech/docs/en/

create database if not exists apollo;

CREATE TABLE apollo.trade_kafka_consumer
(
    id          Int64,
    shop_id     Int64,
    name        String,
    payment     Decimal32(2),
    create_time DateTime,
    update_time DateTime
) engine = Kafka settings kafka_broker_list = 'kafka-broker:9092',
    kafka_topic_list = 'trade',
    kafka_group_name = 'clickhouse.trade',
    kafka_format = 'JSONEachRow',
    kafka_num_consumers = 1;

create table if not exists apollo.trade
(
    id          Int64,
    shop_id     Int64,
    name        String,
    payment     Decimal32(2),
    create_time DateTime,
    update_time DateTime
) engine = ReplacingMergeTree(update_time) partition by toYYYYMMDD(create_time)
      order by (shop_id, id);

-- create materialized view apollo.trade_mv to apollo.trade as
-- select *
-- from apollo.trade_kafka_consumer;

create table apollo.shop_daily_stats
(
    shop_id                  Int64,
    stats_day                Date,
    create_num               Int64,
    create_amount            Decimal64(2),
    paid_num                 Int64,
    paid_amount              Decimal64(2),
    closed_before_pay_num    Int64,
    closed_before_pay_amount Decimal64(2)
) engine = AggregatingMergeTree() partition by shop_id
      order by (shop_id, stats_day);

-- create materialized view apollo.shop_daily_stats_mv to apollo.shop_daily_stats
-- as
-- select // TODO:  from trade
