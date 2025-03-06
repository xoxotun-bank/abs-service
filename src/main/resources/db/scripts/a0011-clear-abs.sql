--liquibase formatted sql
--changeset Sergey Lvov:a0011


truncate periods restart identity cascade;
truncate currencies restart identity cascade ;
truncate capitalization_periods restart identity cascade ;
truncate financial_products restart identity cascade ;
truncate client_categories restart identity cascade;
truncate clients restart identity cascade;
truncate categories restart identity cascade;
