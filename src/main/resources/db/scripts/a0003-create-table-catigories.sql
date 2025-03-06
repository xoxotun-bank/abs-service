--liquibase formatted sql
--changeset Bogdan Terehin:a0003
create table categories (
    id bigserial primary key,
    name varchar(255) not null,
    constraint unique_category unique (name)
)