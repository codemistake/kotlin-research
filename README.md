# Демо проект

## Запуск
cd vm && docker-compose up -d

Для перезапуска через IDE, добавить конфигурацию Shell Script, назвать, например "RestartApp":
- Поставить галочку execute: script text
- Text: docker-compose restart -t 5 app
- Рабочая директория: project dir + /vm
- Добавить beforeLaunch - gradle shadowJar
- Activate tool window: false

Для подключения к JVM debug добавить конфигурацию Remote JVM Debug:
- Host: 127.0.0.1
- Port: 5005
- Добавить beforeLaunch - Shell Script "RestartApp"
- Activate tool window: false

Добавить в /etc/hosts => 127.0.0.1 psp-postgres psp-clickhouse psp-kafka psp-zookeeper psp-liquibase psp-app psp-loki psp-mysql

## Apache Thrift
Сервер реализует контракты thrift из репозитория psp/thrift. Сервер thrift доступен локально на порту 9080
## Импорт fdw postgres на стендах
```sql
IMPORT FOREIGN SCHEMA "dbh" FROM SERVER mysql INTO mysql;
IMPORT FOREIGN SCHEMA "default" FROM SERVER clickhouse INTO clickhouse;
```

## Логирование

Пример:

```kotlin
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class LogExample {
    private val logger: Logger = LoggerFactory.getLogger(PingController::class.java)

    fun foo() {
        logger.info("TEST")
    }
}
```

- https://vertx.io/docs/3.8.4/vertx-pg-client/kotlin/ - Документация по пулу соединений к Postgres
- https://micronaut-projects.github.io/micronaut-data/snapshot/guide/index.html - Документация по библиотеке для описания моделей и репозиториев
- https://www.conduktor.io/ - Десктопный UI клиент для локального мониторинга топиков Kafka

## Micronaut 2.5.5-SNAPSHOT Documentation

- [User Guide](https://docs.micronaut.io/snapshot/guide/index.html)
- [API Reference](https://docs.micronaut.io/snapshot/api/index.html)
- [Configuration Reference](https://docs.micronaut.io/snapshot/guide/configurationreference.html)
- [Micronaut Guides](https://guides.micronaut.io/index.html)
---

- [Micronaut Hibernate JPA documentation](https://micronaut-projects.github.io/micronaut-sql/latest/guide/index.html#hibernate)
- [Micronaut HTTP Client documentation](https://docs.micronaut.io/latest/guide/index.html#httpClient)
- [https://hamcrest.org/JavaHamcrest/](https://hamcrest.org/JavaHamcrest/)
- [Micronaut Data R2DBC documentation](https://micronaut-projects.github.io/micronaut-r2dbc/latest/guide/)
- [https://www.testcontainers.org/](https://www.testcontainers.org/)
- [Micronaut Kafka Messaging documentation](https://micronaut-projects.github.io/micronaut-kafka/latest/guide/index.html)
- [Micronaut R2DBC documentation](https://micronaut-projects.github.io/micronaut-r2dbc/latest/guide/)
- [https://r2dbc.io](https://r2dbc.io)
- [Micronaut Hikari JDBC Connection Pool documentation](https://micronaut-projects.github.io/micronaut-sql/latest/guide/index.html#jdbc)
- [Micronaut Management documentation](https://docs.micronaut.io/latest/guide/index.html#management)