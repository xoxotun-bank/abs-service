--liquibase formatted sql
--changeset Bogdan Terehin:a0006
create table client_categories (
    id bigserial primary key,
    category_id bigint not null,
    client_id bigint not null,
    foreign key (category_id) references categories
            on delete cascade,
    foreign key (client_id) references clients
            on delete cascade
)