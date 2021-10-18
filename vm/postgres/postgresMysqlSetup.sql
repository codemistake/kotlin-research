CREATE SCHEMA mysql_fdw;
CREATE EXTENSION mysql_fdw SCHEMA mysql_fdw;
CREATE SERVER mysql FOREIGN DATA WRAPPER mysql_fdw OPTIONS (host 'psp-mysql', port '3306');
CREATE USER MAPPING FOR postgres SERVER mysql OPTIONS (username 'root', password 'root');
CREATE SCHEMA mysql;