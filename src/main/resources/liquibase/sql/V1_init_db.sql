--liquibase formatted sql
--changeset daniel:init_db logicalFilePath:/

create table person
(
    id         bigserial    not null
        constraint person_pkey
            primary key,
    first_name varchar(128) not null,
    last_name  varchar(128) not null,
    address    varchar(256) not null
);

create table courier
(
    id         bigserial    not null
        constraint courier_pkey
            primary key,
    first_name varchar(128) not null,
    last_name  varchar(128) not null
);
create table orders
(
    id         bigserial    not null
        constraint orders_pkey
            primary key,
    product    varchar(256) not null,
    date_order date         not null,
    person_id  bigint       not null
        constraint orders_person_id_fkey
            references person,
    courier_id bigint       not null
        constraint orders_courier_id_fkey
            references courier
);
