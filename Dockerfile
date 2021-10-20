FROM openjdk:8-jdk-alpine
COPY target/lembrete-api-*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]