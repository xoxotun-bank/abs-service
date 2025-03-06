--liquibase formatted sql
--changeset Bogdan Terehin:a0007
create table financial_products (
    id bigserial primary key,
    name varchar(255) not null,
    min_sum decimal not null,
    max_sum decimal not null,
    period_id bigint not null,
    category_id bigint not null,
    can_deposit boolean not null,
    can_withdrawal boolean not null,
    percent decimal not null,
    capitalization_to_same_account boolean not null,
    capitalization_period_id bigint,
    deadline timestamp with time zone not null,
    currency_id bigint not null,
    foreign key (capitalization_period_id) references capitalization_periods,
    foreign key (currency_id) references currencies,
    foreign key (category_id) references categories,
    foreign key (period_id) references periods,
    constraint unique_name unique (name)
)