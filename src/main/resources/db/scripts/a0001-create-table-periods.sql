--liquibase formatted sql
--changeset Bogdan Terehin:a0001
create table periods(
    id bigserial primary key,
    value integer not null,
    constraint unique_period unique (value)
)