create database apollo character set = utf8mb4;

grant all on apollo.* to apollo;

use apollo;

create table if not exists `user`
(
    id            bigint primary key auto_increment,
    shop_name     varchar(128) not null,
    created       datetime     not null,
    last_modified datetime     not null
);

create table if not exists `sms_template`
(
    id            bigint primary key auto_increment,
    content       text     not null,
    created       datetime not null,
    last_modified datetime not null
);

create table if not exists `send_record`
(
    id            bigint primary key auto_increment,
    template_id   bigint   not null,
    params        text     not null,
    created       datetime not null,
    last_modified datetime not null
);

create table if not exists `sync_item`
(
    id       bigint primary key auto_increment,
    version  bigint      not null,
    shop_id  bigint      not null,
    type     varchar(32) not null,
    stop     tinyint     not null,
    progress text        not null,
    start    text        not null,
    end      text        not null
);