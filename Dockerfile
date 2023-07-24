FROM eclipse-temurin:20-alpine
MAINTAINER DIM <correo@dim.com>
# VOLUME /data/dev
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} gestor-estaciones-monolito.jar
ENTRYPOINT ["java", "-jar", "/gestor-estaciones-monolito.jar"]