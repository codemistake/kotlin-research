CREATE SCHEMA clickhouse_fdw;
CREATE EXTENSION clickhouse_fdw;
CREATE SERVER clickhouse FOREIGN DATA WRAPPER clickhouse_fdw OPTIONS(dbname 'default', host 'psp-clickhouse', port '8123');
CREATE USER MAPPING FOR postgres SERVER clickhouse OPTIONS (user 'clickhouse', password '1212');
CREATE SCHEMA clickhouse;
