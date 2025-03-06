--liquibase formatted sql
--changeset Bogdan Terehin:a0005
create table capitalization_periods(
    id bigserial primary key,
    value varchar(255) not null,
    constraint unique_capitalization_period unique (value)
)