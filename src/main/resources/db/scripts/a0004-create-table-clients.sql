--liquibase formatted sql
--changeset Bogdan Terehin:a0004
create table clients (
    id bigserial primary key,
    name varchar(255) not null,
    birth_date date not null,
    passport varchar(255) not null,
    constraint unique_password unique (passport)
)