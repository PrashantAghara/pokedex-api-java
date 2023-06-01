FROM gradle:jdk11 as gradleimage
USER root
COPY . .
RUN gradle clean build

FROM openjdk:11-jdk-slim
USER root
COPY --from=gradleimage /build/libs/*.jar pokedex.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","demo.jar"]