--liquibase formatted sql
--changeset Bogdan Terehin:a0002
create table currencies(
    id bigserial primary key,
    currency varchar(255) not null,
    constraint unique_currency unique (currency)
)