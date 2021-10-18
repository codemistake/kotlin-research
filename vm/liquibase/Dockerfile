FROM liquibase/liquibase:4.3.5

USER root
RUN wget -O /liquibase/lib/clickhouse.jar \
    https://repo1.maven.org/maven2/com/mediarithmics/liquibase-clickhouse/0.6.1/liquibase-clickhouse-0.6.1-shaded.jar

USER liquibase
CMD liquibase --defaultsFile=/liquibase/clickhouse.properties update && \
    liquibase --defaultsFile=/liquibase/postgres.properties update