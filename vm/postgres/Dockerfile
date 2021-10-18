FROM postgres:13.3-alpine

ARG MYSQL_FDW_VERSION=2_6_0
ARG MYSQL_FDW_URL=https://github.com/EnterpriseDB/mysql_fdw/archive/REL-${MYSQL_FDW_VERSION}.tar.gz
ARG MYSQL_SOURCE_FILES=/tmp/clickhouse_fdw

ARG CLICKHOUSE_FDW_VERSION=1.3.0
ARG CLICKHOUSE_FDW_URL=https://github.com/adjust/clickhouse_fdw/archive/refs/tags/${CLICKHOUSE_FDW_VERSION}.tar.gz
ARG CH_SOURCE_FILES=/tmp/mysql_fdw

RUN apk add --no-cache --virtual .rundeps mariadb-dev util-linux-dev curl-dev; \
    apk add --no-cache --virtual .builddeps clang llvm11-dev build-base cmake;

# install mysql fdw
RUN mkdir -p ${MYSQL_SOURCE_FILES}; \
    wget -O - ${MYSQL_FDW_URL} | tar -zx -C ${MYSQL_SOURCE_FILES} --strip-components=1; \
    cd ${MYSQL_SOURCE_FILES}; \
    # bugfix. see https://github.com/EnterpriseDB/mysql_fdw/issues/187
    sed -i 's/ | RTLD_DEEPBIND//' ./mysql_fdw.c; \
    # compilation
    make USE_PGXS=1; \
    make USE_PGXS=1 install;

# install clickhouse fdw
RUN mkdir -p ${CH_SOURCE_FILES}; \
    wget -O - ${CLICKHOUSE_FDW_URL} | tar -zx -C ${CH_SOURCE_FILES} --strip-components=1; \
    cd ${CH_SOURCE_FILES}; \
    mkdir build && cd build; \
    cmake .. ; \
    make && make install;

# del build deps
RUN apk del .builddeps; \
    rm -rf ${MYSQL_SOURCE_FILES} \
    rm -rf ${CH_SOURCE_FILES}