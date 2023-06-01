FROM ubuntu:latest AS build
USER root
RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
COPY . .
RUN chmod +x gradlew
RUN ./gradlew bootJar --no-daemon
FROM openjdk:17-jdk-slim
EXPOSE 8080
COPY --from=build /build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]