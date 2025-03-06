--liquibase formatted sql
--changeset Bogdan Terehin:a0009
alter table financial_products drop constraint unique_name