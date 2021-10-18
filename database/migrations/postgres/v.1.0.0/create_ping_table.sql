--liquibase formatted sql

--changeset psp:postgres/v1.0.0-create_ping_table
--Database: postgresql
CREATE TABLE ping (
    id BIGSERIAL,
    data JSONB,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    PRIMARY KEY (id)
)

--rollback DROP table ping
