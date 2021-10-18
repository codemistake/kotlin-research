FROM openjdk:16

ENV JAR_FILE psp.jar
ENV ENV_NAME ""
ENV LOGBACK_CONFIG_FILE ""

WORKDIR /app
ENTRYPOINT java -Dmicronaut.environments=${ENV_NAME} \
    -Dlogback.configurationFile=${LOGBACK_CONFIG_FILE} \
    -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005 \
    -jar ${JAR_FILE}
