# 서버 기동시 자동 초기화 스크립트
create schema if not exists sample;

create table if not exists sample.board
(
    id             int auto_increment
        primary key,
    title          varchar(255)                          null,
    contents       varchar(8000)                         null,
    create_time    timestamp default current_timestamp() null,
    updated_time   timestamp                             null,
    create_user_id varchar(8000)                         null
);
create table if not exists sample.user
(
    id   varchar(36) default uuid() not null
        primary key,
    name varchar(255)               null,
    ip   varchar(255)               null,
    constraint user_ip_uindex
        unique (ip)
);

