create table if not exists `user`
(
    id            bigint primary key auto_increment,
    shop_name     varchar(128) not null,
    created       datetime     not null,
    last_modified datetime     not null,
    unique key uk_sn (shop_name)
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
    last_modified datetime not null,
    index idx_tid_created (template_id, created)
);

create table if not exists `sync_item`
(
    id       bigint primary key auto_increment,
    version  bigint       not null,
    shop_id  bigint       not null,
    type     varchar(32)  not null,
    stop     bit          not null,
    progress varchar(128) not null,
    start    varchar(128) not null,
    end      varchar(128) not null,
    step     bigint       not null,
    unique key uk_sid_type (shop_id, type),
    index idx_type_st_pg (type, stop, progress)
);
