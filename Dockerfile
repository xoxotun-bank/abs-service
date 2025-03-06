#systax=docker/dockerfile:1

FROM mirror.gcr.io/library/openjdk:17-alpine

COPY target/*.jar abs-service.jar

EXPOSE 8080

CMD exec java $JAVA_OPTS  -jar abs-service.jar
