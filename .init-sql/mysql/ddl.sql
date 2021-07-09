create database apollo character set=utf8mb4;

create table if not exists `user`
(
    id            bigint primary key,
    shop_name     varchar(128) not null,
    created       datetime     not null,
    last_modified datetime     not null
);


create table if not exists `sms_template`
(
    id            bigint primary key,
    content       text     not null,
    created       datetime not null,
    last_modified datetime not null
);


create table if not exists `send_record`
(
    id            bigint primary key,
    template_id   bigint   not null,
    params        text     not null,
    created       datetime not null,
    last_modified datetime not null
);