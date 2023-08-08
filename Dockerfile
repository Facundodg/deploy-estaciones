FROM eclipse-temurin:20-alpine
LABEL DIM <correo@dim.com>
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} gestor-estaciones-monolito.jar
ENTRYPOINT ["java", "-jar", "/gestor-estaciones-monolito.jar"]