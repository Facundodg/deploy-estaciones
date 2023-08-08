FROM openjdk:20
ADD estacion.jar estacion.jar
ENTRYPOINT ["java","-jar","/estacion.jar"]