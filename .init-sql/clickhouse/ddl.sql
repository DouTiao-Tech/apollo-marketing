-- refer to: https://clickhouse.tech/docs/en/

create database if not exists apollo;

create table if not exists apollo.trade
(
    id       Int64,
    shopId   Int64,
    name     String,
    payment  Decimal32(2),
    created  DateTime,
    modified DateTime
) engine = MergeTree()
      order by (shopId, id)
      partition by toYYYYMMDD(created);