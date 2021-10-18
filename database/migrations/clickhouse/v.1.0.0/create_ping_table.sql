--liquibase formatted sql

--changeset psp:clickhouse/v1.0.0-create_ping_table
CREATE TABLE ping (
    ID UInt8,
    `data.message` String,
    created_at DateTime('Europe/Moscow')
) ENGINE MergeTree() ORDER BY ID;
--rollback DROP table ping